package com.project.withus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CreateRoomDto {
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


}