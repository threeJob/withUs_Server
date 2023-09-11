package com.example.WithUs.model;


import com.example.WithUs.dto.CreateRoomDto;
import com.example.WithUs.dto.CreateRoomResposneDto;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="room")
public class Room{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id //Specifies the primary key of an entity
    @Column(name = "ROOM_ID")
    private Long room_id;


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
    private int limitmember;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean is_lock;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String thumbnail;

    @Column(nullable = true)
    private int invite_code;

    @Column
    private String music;

    @Column
    private Boolean is_valid;

    @Column
    private String notice;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column
    private LocalDateTime modifedAt=LocalDateTime.now();

    public Room(CreateRoomDto createRoomDto, int invitecode, String password)
    {
        this.content=createRoomDto.getContent();
        this.name=createRoomDto.getName();
        this.limitmember =createRoomDto.getLimit();
        this.is_lock =createRoomDto.getIs_lock();
        this.thumbnail =createRoomDto.getThumbnail();
        this.music= createRoomDto.getMusic();
        this.owner=createRoomDto.getOwner();
        this.notice=createRoomDto.getNotice();

        this.invite_code=invitecode;
        this.password=password;
        this.is_valid=true;

    }
    public void updateRoom(CreateRoomDto createRoomDto)
    {
        this.content=createRoomDto.getContent();
        this.name=createRoomDto.getName();
        this.limitmember =createRoomDto.getLimit();
        this.is_lock =createRoomDto.getIs_lock();
        this.thumbnail =createRoomDto.getThumbnail();
        this.music= createRoomDto.getMusic();
        this.owner=createRoomDto.getOwner();
        this.notice=createRoomDto.getNotice();


    }


    public Room(CreateRoomResposneDto createRoomResposneDto)
    {
        this.content= createRoomResposneDto.getContent();
        this.owner= createRoomResposneDto.getOwner();
        this.thumbnail=createRoomResposneDto.getThumbnail();
        this.limitmember= createRoomResposneDto.getLimit();
        this.password= createRoomResposneDto.getPassword();
        this.invite_code= createRoomResposneDto.getInvite_code();
        this.music= createRoomResposneDto.getMusic();
        this.is_lock=createRoomResposneDto.getIs_lock();
        this.is_valid=createRoomResposneDto.getIs_valid();
        this.name=createRoomResposneDto.getName();
        this.notice= createRoomResposneDto.getNotice();
    }





    public void setNotice(String notice)
    {
        this.notice=notice;
    }
}

