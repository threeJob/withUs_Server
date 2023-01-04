package com.project.withus.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.withus.dto.CreateRoomDto;
import com.project.withus.service.OAuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class OAuthController {

    public final OAuthService oAuthService;
    @ResponseBody
    @GetMapping("/api/oauth")
    public void kakaoLogin(@RequestParam String code) throws JsonProcessingException {

        oAuthService.kakaologin(code); //서비스가 인가코드를 받아서 로그인 처리해줌
        //성공하면 원하는 화면으로 리다이렉트 해주는 부분도 구현해야함
    }

}
