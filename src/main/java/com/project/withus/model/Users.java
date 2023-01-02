package com.project.withus.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="NAME")
    private String name;

    @Column(nullable = false)
    private String profile_nickname;

    @Column(nullable = false)
    private String account_email;

    //생일
    @Column(nullable = false)
    private String birthday ;

    //출생연도
    @Column(nullable = false)
    private String birthyear ;

    @Column(nullable = false)
    private String phone_number ;

    @Column(nullable = false)
    private Boolean is_valid;





}
