package com.allblue.service;

import java.util.ArrayList;
import java.util.List;

import com.allblue.model.BlueRole;
import com.allblue.model.BlueUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BlueUserService blueUserService;

    @Autowired
    private BlueRoleService blueRoleService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        BlueUser blueUser = blueUserService.getUserInfo(name);
        System.out.println("BlueUser : " + blueUser);
        if (blueUser == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(blueUser.getName(), blueUser.getPassword(),
                blueUser.getStatus() == 1, true, true, true, getGrantedAuthorities(blueUser));
    }


    private List<GrantedAuthority> getGrantedAuthorities(BlueUser blueUser) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        List<BlueRole> list = blueRoleService.getRoleListByUserName(blueUser.getName());

        for (BlueRole blueRole : list) {
            System.out.println("blueRole : " + blueRole);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + blueRole.getCode()));
        }
        System.out.print("authorities :" + authorities);
        return authorities;
    }

}