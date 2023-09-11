package com.example.WithUs.controller;


import com.example.WithUs.dto.CreateRoomDto;
import com.example.WithUs.dto.CreateRoomResposneDto;
import com.example.WithUs.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;


    @PostMapping("/api/room")
    public CreateRoomResposneDto createRoom(@RequestBody CreateRoomDto createRoomDto)
    {
        List<String>hashtags=createRoomDto.getHashtags();
        return roomService.createRoom(createRoomDto,hashtags);
    }

//    @PutMapping("/api/room/{roomId}")
//    public CreateRoomResposneDto updateRoom(@PathVariable Long roomId,@RequestBody CreateRoomDto createRoomDto)
//    {
//        List<String>hashtags=createRoomDto.getHashtags();
//        return roomService.updateRoom(roomId,createRoomDto,hashtags);
//    }
//
//
//    @GetMapping("/api/room/{roomId}")
//    public CreateRoomResposneDto getDetailRoom(@PathVariable Long roomId)
//    {
//        System.out.println("조회할 아이디 : "+roomId);
//        return roomService.getDetailRoom(roomId);
//    }

//    @PostMapping("/api/room/notice/{roomId}")
//    public String noticeRoom(@PathVariable Long roomId,@RequestBody NoticeDto noticeDto)
//    {
//        return roomService.noticeRoom(roomId,noticeDto.getNotice());
//    }



    @DeleteMapping("/api/room/{roomId}")
    public void deleteRoom(@PathVariable Long roomId)
    {
        roomService.deleteRoom(roomId);
    }
}
