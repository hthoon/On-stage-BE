package com.team5.on_stage.admin.dto;

import com.team5.on_stage.user.entity.User;
import com.team5.on_stage.user.enums.Verified;
import lombok.Data;

@Data
public class AwaitingVerificationUser {
    private String username;
    private String nickname;
    private Verified verified;

    public static AwaitingVerificationUser from(User user) {
        AwaitingVerificationUser dto = new AwaitingVerificationUser();

        dto.setUsername(user.getUsername());
        dto.setNickname(user.getNickname());
        dto.setVerified(user.getVerified());

        return dto;
    }
}
