package com.christ.service;

import com.christ.entity.User;
import com.christ.repository.RoleRepository;
import com.christ.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 史偕成
 * @date 2023/08/04 17:10
 **/
@Service
@RequiredArgsConstructor
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user is null");
        }
        user.setRoles(roleRepository.findByUserId(user.getUserId()));
        return user;
    }
}
