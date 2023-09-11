package com.example.WithUs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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


    private String thumbnail;


    private String music;


    private List<String> hashtags;


    private String notice;
}