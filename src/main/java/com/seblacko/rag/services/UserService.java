package com.seblacko.rag.services;

import com.seblacko.rag.util.UserDetails;

public class UserService {


    public UserDetails getAdminByEmail(String email,String password){
        return new UserDetails(email,password);
    }
}
