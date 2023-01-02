package com.project.withus.service;

import com.project.withus.dto.CreateRoomDto;
import com.project.withus.dto.CreateRoomResposneDto;
import com.project.withus.model.Room;
import com.project.withus.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public ResponseEntity<CreateRoomResposneDto> createRoom(CreateRoomDto createRoomDto){
        Room room=new Room(createRoomDto);
        roomRepository.save(room);
        CreateRoomResposneDto createRoomResposneDto=new CreateRoomResposneDto(room);
        return new ResponseEntity<>(createRoomResposneDto, HttpStatus.valueOf(200));
    }
}
