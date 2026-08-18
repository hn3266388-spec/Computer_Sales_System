package com.example.MyProject.AUTH.service;

import com.example.MyProject.AUTH.dto.address.request.UseraddressRequest;
import com.example.MyProject.AUTH.dto.address.response.UseraddressResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UseraddressService {
    void createUseraddress(UseraddressRequest useraddressRequest);
    void updateUseraddress(Long id,UseraddressRequest useraddressRequest);
    Page<UseraddressResponse> showList(Pageable pageable);
    void deleteUseraddress(Long id);
    List<UseraddressResponse> searchUseraddress(String nameUseraddress);
    List<UseraddressResponse> listaddress();
}
