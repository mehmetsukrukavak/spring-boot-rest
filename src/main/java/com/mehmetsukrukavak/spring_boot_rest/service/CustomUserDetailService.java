package com.mehmetsukrukavak.spring_boot_rest.service;


import com.mehmetsukrukavak.spring_boot_rest.model.User;
import com.mehmetsukrukavak.spring_boot_rest.model.UserPrincipal;
import com.mehmetsukrukavak.spring_boot_rest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       User user =  repo.findByUsername(username);
       if (user == null) {
           throw new UsernameNotFoundException(username + " not found");
       }
        return new UserPrincipal(user);
    }
}
