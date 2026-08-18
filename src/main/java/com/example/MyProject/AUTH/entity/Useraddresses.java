package com.example.MyProject.AUTH.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user_addresses")
@Getter
@Setter
public class Useraddresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;
    private  String username;
    private String phone;
    private String address;
    private Boolean defaultaddress;
}
