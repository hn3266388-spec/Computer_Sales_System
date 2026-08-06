package com.example.MyProject.AUTH.repository;


import com.example.MyProject.AUTH.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
        List<Account> findByGmailOrAccountname(String accountname, String gmail);
        boolean existsBygmail(String gmail);
        Optional<Account> findBygmail(String gmail);
}
