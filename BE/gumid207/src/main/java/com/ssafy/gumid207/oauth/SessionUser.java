package com.ssafy.gumid207.oauth;

import java.io.Serializable;

import com.ssafy.gumid207.entity.User;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getId();
        this.email = user.getEmail();
        this.picture = user.getNickName();
    }
}
