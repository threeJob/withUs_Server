package com.example.WithUs.service;

import com.example.WithUs.model.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.WithUs.dto.KakaoAccountDto;
import com.example.WithUs.dto.KakaoTokenDto;
import com.example.WithUs.model.RefreshToken;
import com.example.WithUs.model.Users;
import com.example.WithUs.repository.RefreshTokenRepository;
import com.example.WithUs.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor //final로 선언된것만 생성자 만들어줌
@Component
public class OAuthService {

	@Value("${kakao.client-id}")
	private String client_id;
	private static final String REDIRECT_URI = "http://localhost:8080/login/oauth2/code/kakao";
	private static final String GRANT_TYPE = "authorization_code";
	//    private static final String CLIENT_ID = "{secret.CLIENT_ID}";
	private final UsersRepository usersRepository;
	private final RefreshTokenRepository refreshTokenRepository;

	public KakaoAccountDto kakaologin(String code) throws JsonProcessingException {
		String accessToken = getAccessToken(code).getAccessToken();
		KakaoAccountDto kakaoAccountDto = getKakaoUserInfo(accessToken);
//        Users users= Users.builder()
//                .kakaoId(kakaoAccountDto.getAccount_email())
//                .build();
		return kakaoAccountDto;
	}

	//인가코드로 액세스 토큰, 리프레시 토큰 받기
	private KakaoTokenDto getAccessToken(String code) throws JsonProcessingException {
		//code=인가코드
		//https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-token
		//맞춰서 코드,변수 설정해주기

		// 1. "인가 코드"로 "액세스 토큰" 요청
		// HTTP Header 생성 (카카오에서 요구하는 헤더 내용)
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HTTP Body 생성 (카카오에서 요구하는 바디 내용)
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		//카카오에서 요구하는 Request Parameter임.
		body.add("grant_type", "authorization_code");
		body.add("client_id", client_id); //네이티브 앱 키
		body.add("redirect_uri", "http://localhost:8080/api/oauth"); //성공했을 때 리다이렉트 주소
		body.add("code", code);

		// HTTP 요청 보내기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(body, headers);
		RestTemplate rt = new RestTemplate(); //서버간 api 호출
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);
		//exchange는 api를 호출하는 메소드


		// HTTP 응답 (JSON) -> 액세스 토큰 파싱
		String responseBody = response.getBody(); //카카오로부터 받은 response에서 body만 추출
		ObjectMapper objectMapper = new ObjectMapper(); //json 형태를 자바에서 사용하기 위해서?
		JsonNode jsonNode = objectMapper.readTree(responseBody); //String인 body를 json 객체 형태로 변환
		String accessToken = jsonNode.get("access_token").asText(); //access_token이라고 담아준 부분 추출

		String refreshToken = jsonNode.get("refresh_token").asText();

		RefreshToken refreshToken1 = new RefreshToken(refreshToken);
		refreshTokenRepository.save(refreshToken1);

		//refresh 토큰은 어디에?
		System.out.println("access Token: " + accessToken);
		System.out.println("refresh Token: " + refreshToken);

		return new KakaoTokenDto(accessToken, refreshToken);
	}


	private KakaoAccountDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();

		// 2. 토큰으로 카카오 API 호출
		// HTTP Header 생성
		headers.add("Authorization", "Bearer " + accessToken); //헤더에 accesstoken을 담아서 보내줌
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		RestTemplate rt = new RestTemplate();
		// HTTP 요청 보내기
		HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
		//헤더에 액세스 토큰 담아서 요청 보내기. response로 요청 받기
		ResponseEntity<String> response = rt.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoUserInfoRequest,
				String.class
		);

		String responseBody = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		//https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info-response 참고
		responseBody = response.getBody();
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		String kakaoid = jsonNode.get("id").asText(); //앱과 연결된 사용자 회원번호
		String nickname = jsonNode.get("properties")
				.get("nickname").asText();
		String email = jsonNode.get("kakao_account")
				.get("email").asText();
		String birthday = jsonNode.get("kakao_account")
				.get("birthday").asText();
		System.out.println("카카오 사용자 정보: " + kakaoid + ", " + nickname + ", " + email + "," + birthday);


		//받아온 카카오id 정보가 DB에 있는지 확인해보고 없으면 저장, 있으면 그냥 넘어감
		if (!usersRepository.existsUsersByKakaoId(kakaoid)) //없으면
		{
			Users user = Users.builder()
					.kakaoId(kakaoid)
					.email(email)
					.birthday(birthday)
					.role(UserRole.USER)
					.nickname(nickname)
					.build();
			usersRepository.save(user);
		}
		KakaoAccountDto accountDto = new KakaoAccountDto(nickname, email, birthday);
		return accountDto;
	}

	private KakaoAccountDto getInfoByAccessToken(String accessToken) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();

		// 2. 토큰으로 카카오 API 호출
		// HTTP Header 생성
		headers.add("Authorization", "Bearer " + accessToken); //헤더에 accesstoken을 담아서 보내줌
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		RestTemplate rt = new RestTemplate();
		// HTTP 요청 보내기
		HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
		//헤더에 액세스 토큰 담아서 요청 보내기. response로 요청 받기
		ResponseEntity<String> response = rt.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoUserInfoRequest,
				String.class
		);

		String responseBody = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		//https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info-response 참고
		responseBody = response.getBody();
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		String kakaoid = jsonNode.get("id").asText(); //앱과 연결된 사용자 회원번호
		String nickname = jsonNode.get("properties")
				.get("nickname").asText();
		String email = jsonNode.get("kakao_account")
				.get("email").asText();
		String birthday = jsonNode.get("kakao_account")
				.get("birthday").asText();
		System.out.println("카카오 사용자 정보: " + kakaoid + ", " + nickname + ", " + email + "," + birthday);


		//받아온 카카오id 정보가 DB에 있는지 확인해보고 없으면 저장, 있으면 그냥 넘어감
		if (!usersRepository.existsUsersByKakaoId(kakaoid)) //없으면
		{
			Users user = Users.builder()
					.kakaoId(kakaoid)
					.email(email)
					.birthday(birthday)
					.role(UserRole.USER)
					.nickname(nickname)
					.build();
			usersRepository.save(user);
		}
		KakaoAccountDto accountDto = new KakaoAccountDto(nickname, email, birthday);
		return accountDto;
	}

}
