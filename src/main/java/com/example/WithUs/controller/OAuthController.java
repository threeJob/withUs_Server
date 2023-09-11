package com.example.WithUs.controller;


//import com.example.WithUs.service.UserService;
import com.example.WithUs.dto.KakaoAccountDto;
import com.example.WithUs.service.OAuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OAuthController {

    //전화번호를 먼저 받아서 db에서 전화번호 있는지 조회하기
    //있으면 -> true
    //없으면 -> false

    public final OAuthService oAuthService;
    @GetMapping("/api/oauth")
    public KakaoAccountDto kakaoLogin(@RequestParam(name = "code") String code) throws Exception {

       return oAuthService.kakaologin(code); //서비스가 인가코드를 받아서 로그인 처리해줌
        //성공하면 원하는 화면으로 리다이렉트 해주는 부분도 구현해야함
    }


}
