package com.example.WithUs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="refresh_token")
@Entity
@Getter
@NoArgsConstructor
public class RefreshToken {

    @Id
    private String refreshToken;



    public RefreshToken(String refreshToken)
    {
        this.refreshToken=refreshToken;
    }
}
