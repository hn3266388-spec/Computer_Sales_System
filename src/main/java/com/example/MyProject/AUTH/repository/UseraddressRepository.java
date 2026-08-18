package com.example.MyProject.AUTH.repository;

import com.example.MyProject.AUTH.entity.Useraddresses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UseraddressRepository extends JpaRepository<Useraddresses,Long> {
    List<Useraddresses> findByAccountId(Long accountid);
    List<Useraddresses> findByUsername(String username);
}
