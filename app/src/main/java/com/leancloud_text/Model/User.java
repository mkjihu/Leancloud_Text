package com.leancloud_text.Model;

/**
 * Created by kevinh on 2017/7/25.
 */

public class User {

    private String objectId;
    private String username;
    private String nickname;//-暱稱
    private User_image image;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User_image getImage() {
        return image;
    }

    public void setImage(User_image image) {
        this.image = image;
    }
}
