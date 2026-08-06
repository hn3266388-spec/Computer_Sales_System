package com.example.MyProject.AUTH.security.custom;


import com.example.MyProject.AUTH.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        return accountRepository.findBygmail(gmail).orElseThrow(()-> new UsernameNotFoundException("User not found: " + gmail));
    }
}
