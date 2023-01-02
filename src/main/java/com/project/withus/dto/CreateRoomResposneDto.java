package com.project.withus.dto;

import com.project.withus.model.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CreateRoomResposneDto {
    private int room_id;
    private String name;
    private String owner;
    private int limit;
    private String content;
    private Boolean is_lock;
    private String password;
    private String image;
    private int invite_code;
    private String music;
    private Boolean is_valid;
    private String hashtag;

    public CreateRoomResposneDto(Room room)
    {
        this.content=room.getContent();
        this.hashtag=room.getHashtag();
        this.image=room.getImage();
        this.invite_code=room.getInvite_code();
        this.is_valid=room.getIs_valid();
        this.is_lock=room.getIs_lock();
        this.limit=room.getLimittime();
        this.music=room.getMusic();
        this.name=room.getName();
        this.owner=room.getOwner();
        this.password=room.getPassword();
        this.room_id=room.getRoom_id();
    }
}
