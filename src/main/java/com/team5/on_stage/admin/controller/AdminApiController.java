package com.team5.on_stage.admin.controller;

import com.team5.on_stage.admin.dto.AwaitingVerificationUser;
import com.team5.on_stage.admin.service.AdminApiService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminApiController {
    private final AdminApiService adminApiService;

    @Operation(summary = "인증을 요청한 사용자 조회 API", description = "인증 요청한 사용자들을 조회한다.")
    @GetMapping("/verify/awaiting")
    public ResponseEntity<List<AwaitingVerificationUser>> viewAwaitingVerificationUsers() {
        List<AwaitingVerificationUser> result =  adminApiService.getUsersAwaitingVerification();

        return ResponseEntity.ok(result);
    }


    @Operation(summary = "SMS 인증을 통한 신청 확인 처리 API", description = "관리자가 신청 요청을 수락한다.")
    @PostMapping("/verify/{username}")
    public ResponseEntity<Void> acceptVerifyRequest(@PathVariable String username) {
        adminApiService.acceptVerifyRequest(username);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "SMS 인증을 통한 신청 확인 처리 API", description = "관리자가 신청 요청을 수락한다.")
    @PostMapping("/verify/{username}/reject")
    public ResponseEntity<Void> rejectVerifyRequest(@PathVariable String username) {
        adminApiService.rejectVerifyRequest(username);

        return ResponseEntity.ok().build();
    }
}
