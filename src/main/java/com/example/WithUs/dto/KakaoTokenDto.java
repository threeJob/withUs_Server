package com.example.WithUs.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoTokenDto {
    private String accessToken;
    private String refreshToken;
//    private Long expiresIn;
//    private Long refreshTokenExpiresIn;

    public static KakaoTokenDto fail() {
        return new KakaoTokenDto(null, null);
    }

    public KakaoTokenDto(final String accessToken, final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
