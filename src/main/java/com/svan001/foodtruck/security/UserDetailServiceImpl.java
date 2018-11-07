package com.svan001.foodtruck.security;

import com.svan001.foodtruck.domain.User;
import com.svan001.foodtruck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find user with username : " + username));
        List<SimpleGrantedAuthority> authorities = convertAuthorities(principal);

        // Only need username + password, rest is defaulted
        return new UserDetailsImpl(
                principal.getUsername(),
                principal.getEncryptedPassword(),
                authorities
        );
    }

    private List<SimpleGrantedAuthority> convertAuthorities(User principal) {
        return principal.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().getLabel()))
                .collect(Collectors.toList());
    }
}
