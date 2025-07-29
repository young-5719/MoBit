package com.tj703.mobit.controller;

import com.tj703.mobit.dto.response.UserAssetResponseDto;
import com.tj703.mobit.service.UserAssetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assets")
public class UserAssetController {

    private final UserAssetService userAssetService;

    @Operation(summary = "유저 보유 자산 조회", description = "더미 유저의 userNo를 기준으로 보유 자산 목록을 조회합니다.")
    @GetMapping("/{userNo}")
    public List<UserAssetResponseDto> getAssets(@PathVariable int userNo) {
        return userAssetService.getUserAssets(userNo);
    }
}