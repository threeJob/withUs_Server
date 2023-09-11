package com.example.WithUs.service;


import com.example.WithUs.dto.CreateRoomDto;
import com.example.WithUs.dto.CreateRoomResposneDto;
import com.example.WithUs.model.Hashtag;
import com.example.WithUs.model.HashtagToRoom;
import com.example.WithUs.model.Room;
import com.example.WithUs.repository.HashtagRepository;
import com.example.WithUs.repository.HashtagToRoomRepository;
import com.example.WithUs.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HashtagToRoomRepository hashtagToRoomRepository;
    private final HashtagRepository hashtagRepository;




    public CreateRoomResposneDto createRoom(CreateRoomDto createRoomDto, List<String> hashtags){

        Room room=new Room(createRoomDto,createInvitecode(),createPassword()); //해시태그 제외한 room 객체

        roomRepository.save(room);



        for(String hashtag:hashtags)
        {
            //리팩토링 방향 : 중간에 해시태그테이블에 이미 존재하는 해시태그인지 검사

            Hashtag hashtag1=new Hashtag(hashtag);
            hashtagRepository.save(hashtag1);//리스트의 해시태그 이름들로 하나하나 해시태그 만들기
            HashtagToRoom hashtagToRoom=new HashtagToRoom(room.getRoom_id(),hashtag1.getId()); //관계테이블 생성하기
            hashtagToRoomRepository.save(hashtagToRoom);//관계테이블 저장하기

        }
        System.out.println("방 제목: "+createRoomDto.getName());
        CreateRoomResposneDto createRoomResposneDto=new CreateRoomResposneDto(room);

        return createRoomResposneDto; //여기에 room_id가 제대로 들어가려면 어떻게 해야하는가?
    }

//    public Boolean IsHashtagExist(String hashtag0){
//
//    }

    public String noticeRoom(Long roomId,String notice)
    {
        Room room=roomRepository.findById(roomId).orElseThrow(()->new IllegalArgumentException("방을 찾을 수 없습니다"));
        room.setNotice(notice);
        roomRepository.save(room);
        System.out.println("방id: "+room.getRoom_id()+" 추가된 공지사항 "+room.getNotice());
        return notice;
    }

//    public CreateRoomResposneDto updateRoom(Long roomId,CreateRoomDto createRoomDto,List<String>hashtags)
//    {
//        Room room=roomRepository.findById(roomId).orElseThrow(()->new IllegalArgumentException("방을 찾을 수 없습니다"));
//        room.updateRoom(createRoomDto);
//        String password=room.getPassword();
//        int inviteCode=room.getInvite_code();
//        for(String hashtag:hashtags)
//        {
//            //리팩토링 방향 : 중간에 해시태그테이블에 이미 존재하는 해시태그인지 검사
//
//            Hashtag hashtag1=new Hashtag(hashtag); //리스트의 해시태그 이름들로 하나하나 해시태그 만들기
//            hashtagRepository.save(hashtag1);
//            HashtagToRoom hashtagToRoom=new HashtagToRoom(room.getRoom_id(),hashtag1.getId()); //관계테이블 생성하기
//            hashtagToRoomRepository.save(hashtagToRoom);//관계테이블 저장하기
//
//        }
//
//
//        CreateRoomResposneDto createRoomResposneDto=DtoConverter.roomconverter(room,password,inviteCode,hashtags);
//        return createRoomResposneDto;
//    }

//    public CreateRoomResposneDto getDetailRoom(Long roomId)
//    {
//
//        Room room=roomRepository.findById(roomId).orElseThrow(()->new IllegalArgumentException("방을 찾을 수 없습니다"));
//
//        String password=room.getPassword();
//        int inviteCode=room.getInvite_code();
//        List<HashtagToRoom> hashtagToRoom=hashtagToRoomRepository.findAllByRoomId(room.getRoom_id());
//        List<String> hashtags=new ArrayList<>();
//        for(HashtagToRoom hs:hashtagToRoom)
//        {
//
//
//            Optional<Hashtag> hashtag=hashtagRepository.findById(hs.getHashtagId());
//            hashtags.add(hashtag.get().getName());
//
//        }
//
//        CreateRoomResposneDto createRoomResposneDto=DtoConverter.roomconverter(room,password,inviteCode,hashtags);
//
//
//        System.out.println("조회할 방번호 : "+createRoomResposneDto.getRoom_id());
//        System.out.println("조회할 방제목 : " +createRoomResposneDto.getName());
//
//        return createRoomResposneDto;
//
//    }

    public int createInvitecode()
    {
        Random random=new Random();


        int createNum = 0;  			//1자리 난수
        String ranNum = ""; 			//1자리 난수 형변환 변수
        int letter    = 6;			//난수 자릿수:6
        String inviteCode = "";  		//결과 난수

        for (int i=0; i<letter; i++) {

            createNum = random.nextInt(9);		//0부터 9까지 올 수 있는 1자리 난수 생성
            ranNum =  Integer.toString(createNum);  //1자리 난수를 String으로 형변환
            inviteCode += ranNum;			//생성된 난수(문자열)을 원하는 수(letter)만큼 더하며 나열
        }

        int invite_code=Integer.parseInt(inviteCode);
        return invite_code;
    }

    public String createPassword()
    {
        Random random=new Random();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        //대소문자+숫자 8자리로 된 랜덤 비밀번호 생성
        String password = random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(8)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return password;
    }

    public void deleteRoom(Long roomId)
    {
        roomRepository.deleteById(roomId);
        System.out.println("삭제 완료!");
    }
}