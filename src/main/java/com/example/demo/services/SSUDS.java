
package com.example.demo.services;

import com.example.demo.models.AppRole;
import com.example.demo.models.User;
import com.example.demo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUDS implements UserDetailsService {

    private UserRepository userRepository;

    public SSUDS(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User theUser = userRepository.findByUsername(s);
        if (theUser == null) {
            throw new UsernameNotFoundException("Unable to find that user!!!!");
        }
        return new org.springframework.security.core.userdetails.User(theUser.getUsername(), theUser.getPassword(), myAuthorities(theUser));
    }

    private Set<GrantedAuthority> myAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (AppRole role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}

