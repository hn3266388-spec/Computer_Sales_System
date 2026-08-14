package com.example.MyProject.AUTH.repository;

import com.example.MyProject.AUTH.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findAllByToken(String token);

    void deleteAllByAccount_Id(Long accountId);
}
