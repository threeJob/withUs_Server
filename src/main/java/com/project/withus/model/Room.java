package com.project.withus.model;

import com.project.withus.dto.CreateRoomDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id //Specifies the primary key of an entity
    @Column(name = "ROOM_ID")
    private int room_id;

    @Column(nullable = false)
    private String name;


    //단방향
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID") //외래키를 매핑할 때 사용
//    private User user;
    //Foreign Key로 하려면 User를 참조해야하는데?
    //근데 jpa는 OnetoOne 단방향을 지원하지 않음
    //그렇다고 양방향으로 하면 수정할 때 문제가 생길수도
    //https://ict-nroo.tistory.com/126
    //방 하나당 유저가 많을수도 있고, 유저가 여러 방에 속할수도 있으므로


    @Column(nullable = false)
    private String owner;


    @Column(nullable = false)
    private int limittime;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean is_lock;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true,unique = true)
    private int invite_code;

    @Column
    private String music;

    @Column
    private Boolean is_valid;

    @Column
    private String hashtag;


    public Room(CreateRoomDto createRoomDto)
    {
        this.content=createRoomDto.getContent();
        this.name=createRoomDto.getName();
        this.limittime =createRoomDto.getLimit();
        this.is_lock=createRoomDto.getIs_lock();
        this.hashtag=createRoomDto.getHashtag();
        this.is_valid= createRoomDto.getIs_valid();
        this.image=createRoomDto.getImage();
        this.music= createRoomDto.getMusic();
        this.invite_code=createRoomDto.getInvite_code();
        this.password= createRoomDto.getPassword();
        this.owner=createRoomDto.getOwner();
    }
}

