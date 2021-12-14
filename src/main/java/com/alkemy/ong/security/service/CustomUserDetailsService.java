package com.alkemy.ong.security.service;

import com.alkemy.ong.dto.UserAuthenticatedResponseDto;
import com.alkemy.ong.model.User;
import com.alkemy.ong.security.jwt.JwtProviderImpl;
import com.alkemy.ong.security.util.SecurityUtils;
import com.alkemy.ong.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserService userService;
    private final JwtProviderImpl jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().getName()));

        return UserDetailsImpl.builder()
                .user(user).id(user.getUserId())
                .username(email)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

    public UserAuthenticatedResponseDto getUserDetails(String authorizationHeader) {
        String username = jwtProvider.extractUsername(authorizationHeader);
        Optional<User> user= userService.findByEmail(username);
        return UserAuthenticatedResponseDto.builder()
                .id(user.get().getUserId())
                .firstName(user.get().getFirstName())
                        .lastName(user.get().getLastName())
                                .email(user.get().getEmail())
                                        .photo(user.get().getPhoto()).build();
        }

}
