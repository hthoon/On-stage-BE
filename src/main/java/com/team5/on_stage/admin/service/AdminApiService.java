package com.team5.on_stage.admin.service;

import com.team5.on_stage.admin.dto.AwaitingVerificationUser;
import com.team5.on_stage.global.constants.ErrorCode;
import com.team5.on_stage.global.exception.GlobalException;
import com.team5.on_stage.user.entity.User;
import com.team5.on_stage.user.enums.Verified;
import com.team5.on_stage.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminApiService {
    private final UserRepository userRepository;

    public List<AwaitingVerificationUser> getUsersAwaitingVerification() {
        List<User> users = userRepository.getAllUsersInProgress();

        return users.stream()
                .map(AwaitingVerificationUser::from)
                .toList();
    }


    public void acceptVerifyRequest(String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new GlobalException(ErrorCode.USER_NOT_FOUND);
        }

        user.setVerified(Verified.VERIFIED);

        userRepository.save(user);
    }

    public void rejectVerifyRequest(String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new GlobalException(ErrorCode.USER_NOT_FOUND);
        }

        user.setVerified(Verified.UNVERIFIED);

        userRepository.save(user);
    }
}
