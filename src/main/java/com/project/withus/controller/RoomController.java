package com.project.withus.controller;

import com.project.withus.dto.CreateRoomDto;
import com.project.withus.dto.CreateRoomResposneDto;
import com.project.withus.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/api/room")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomDto createRoomDto)
    {
        return roomService.createRoom(createRoomDto);
    }
}
