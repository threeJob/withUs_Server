package com.example.WithUs.dto;

import com.example.WithUs.model.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CreateRoomResposneDto {
    private Long room_id;
    private String name;
    private String owner;
    private int limit;
    private String content;
    private Boolean is_lock;
    private String password;
    private String thumbnail;
    private int invite_code;
    private String music;
    private Boolean is_valid; //방 활성여부
    private List<String> hashtags;
    private String notice;


    public CreateRoomResposneDto(Room room)
    {
        this.name=room.getName();
        this.room_id=room.getRoom_id();
        this.owner=room.getOwner();
        this.limit=room.getLimitmember();
        this.content=room.getContent();
        this.is_lock=room.getIs_lock();
        this.password=room.getPassword();
        this.thumbnail=room.getThumbnail();
        this.invite_code=room.getInvite_code();
        this.music=room.getMusic();
        this.is_valid=room.getIs_valid();
        this.notice=room.getNotice();
    }


}
