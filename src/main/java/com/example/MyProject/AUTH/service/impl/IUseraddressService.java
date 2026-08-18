package com.example.MyProject.AUTH.service.impl;

import com.example.MyProject.AUTH.dto.address.request.UseraddressRequest;
import com.example.MyProject.AUTH.dto.address.response.UseraddressResponse;
import com.example.MyProject.AUTH.entity.Account;
import com.example.MyProject.AUTH.entity.Useraddresses;
import com.example.MyProject.AUTH.mapper.UseraddressMapper;
import com.example.MyProject.AUTH.repository.UseraddressRepository;
import com.example.MyProject.AUTH.security.custom.CustomUserDetailsService;
import com.example.MyProject.AUTH.service.UseraddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IUseraddressService implements UseraddressService {
    @Autowired
    private final UseraddressMapper useraddressMapper;
    @Autowired
    private final UseraddressRepository useraddressRepository;

    @Override
    public void createUseraddress(UseraddressRequest useraddressRequest) {
        Account account=(Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long accountid= account.getId();
        List<Useraddresses> useraddressList = useraddressRepository.findByAccountId(accountid);
        if (useraddressList.size() >= 5) {
            throw new RuntimeException("mỗi Account chỉ được đăng kí 10 địa chỉ nhận hàng");
        }
        Useraddresses useraddresses=useraddressMapper.toEntity(useraddressRequest);
        useraddresses.setAccount(account);
        useraddressRepository.save(useraddresses);
    }

    @Override
    public void updateUseraddress(Long id, UseraddressRequest useraddressRequest) {
        Useraddresses useraddresses = useraddressRepository.findById(id).orElseThrow(() -> new RuntimeException("useraddresses not exitst!"));
        useraddressMapper.updateUseraddress(useraddressRequest, useraddresses);
        useraddressRepository.save(useraddresses);
    }

    @Override
    public Page<UseraddressResponse> showList(Pageable pageable) {
        Page<Useraddresses> responsePage = useraddressRepository.findAll(pageable);
        return responsePage.map(useraddressMapper::toRespon);
    }

    @Override
    public void deleteUseraddress(Long id) {
        useraddressRepository.deleteById(id);
    }

    @Override
    public List<UseraddressResponse> searchUseraddress(String nameUseraddress) {
        List<Useraddresses> useraddresses = useraddressRepository.findByUsername(nameUseraddress);
        return useraddresses.stream().map(useraddressMapper::toRespon).toList();
    }

    @Override
    public List<UseraddressResponse> listaddress() {
        Account userDetails=(Account) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Long accountid= userDetails.getId();
        List<Useraddresses> useraddresses=useraddressRepository.findByAccountId(accountid);
        return useraddresses.stream().map(useraddressMapper::toRespon).toList();
    }
}
