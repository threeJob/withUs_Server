package com.example.WithUs.model;


import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name="hashtagToRoom")
@NoArgsConstructor
@Getter
@Entity
public class HashtagToRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private Long roomId;

    @Column
    private Long hashtagId;



    public HashtagToRoom(Long roomId,Long hashtagId) {

        this.roomId=roomId;
        this.hashtagId=hashtagId;
    }


}
