package com.sparta.basic4.application.service.user;

import com.sparta.basic4.application.dtos.user.SignupRequestDto;
import com.sparta.basic4.application.dtos.user.UserResponse;
import com.sparta.basic4.application.exceptions.NotFoundException;
import com.sparta.basic4.domain.User;
import com.sparta.basic4.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public UserResponse createUser(SignupRequestDto request) {
        // 사용자 등록
        User user = User.create(
                request.getUsername(),
                getEncodedPassword(request.getPassword()),
                request.getEmail());
        userRepository.save(user);
        return UserResponse.of(user);
    }

    public UserResponse getUser(UserDetails loginUser) {
        User user = userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new NotFoundException("유저가 존재하지 않습니다."));;
        return UserResponse.of(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유저가 존재하지 않습니다."));

    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
