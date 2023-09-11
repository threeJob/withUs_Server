package com.example.WithUs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class KakaoAccountDto {

    //나머지는 사업자 정보를 등록하여 비즈 앱으로 전환하고 비즈니스 채널을 연결해야 열람 가능
    private String profile_nickname;
    private String account_email;
//    private String name;
//    private String birthyear ;
    private String birthday;
//    private String phone_number;

}
