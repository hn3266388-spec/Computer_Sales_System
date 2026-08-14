package com.example.MyProject.AUTH.security.service;

import com.example.MyProject.AUTH.entity.Account;
import com.example.MyProject.AUTH.entity.RefreshToken;
import com.example.MyProject.AUTH.repository.AccountRepository;
import com.example.MyProject.AUTH.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${jwt.refresh-expiration}")
    private Long refreshTokenExpirationMs;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccountRepository accountRepository;
    @Transactional
    public RefreshToken createRefreshToken(Long account_id){
        Account account= accountRepository.findById(account_id).orElseThrow(()-> new IllegalArgumentException("User not exist"));
        refreshTokenRepository.deleteAllByAccount_Id(account_id);
        RefreshToken refreshToken=RefreshToken.builder()
                .account(account)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshTokenExpirationMs))
                .revoked(false)
                .build();
        return refreshTokenRepository.save(refreshToken);
    }
    public Optional<RefreshToken> finByToken(String token){
        return refreshTokenRepository.findAllByToken(token);
    }
    public RefreshToken verifyExpiration(RefreshToken refreshToken){
        if(refreshToken.getExpiryDate().compareTo(Instant.now())<0){
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
            throw new RuntimeException("Refresh token expiry!");
        }
        return refreshToken;
    }
    @Transactional
    public void revokeAllAcoountToken(Long account_id){
        refreshTokenRepository.deleteAllByAccount_Id(account_id);
    }
}
