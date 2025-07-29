package com.tj703.mobit.service;

import com.tj703.mobit.dto.response.UserAssetResponseDto;
import com.tj703.mobit.entity.UserAsset;
import com.tj703.mobit.repository.UserAssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAssetService {
    private final UserAssetRepository userAssetRepository;

    public List<UserAssetResponseDto> getUserAssets(int userNo) {
        List<UserAsset> assets = userAssetRepository.findByUserUserNo(userNo);

        return assets.stream()
                .map(asset -> new UserAssetResponseDto(
                        asset.getMarket(),
                        asset.getQuantity(),
                        asset.getAvgPrice()
                ))
                .collect(Collectors.toList());
    }
}
