package com.example.WithUs.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="hashtag")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="HASHTAG_ID")
    private Long id;

    @Column
    private String name;







//    @ManyToOne
//    @JoinColumn(name="room_id")//room_id라는 이름을 컬럼에 join함
//    private Room room;


    public Hashtag(String hashtag)
    {
        this.name=hashtag;
    }



}
