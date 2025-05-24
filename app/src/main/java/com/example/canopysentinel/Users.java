package com.example.canopysentinel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Users {
    @NonNull
    private String userId = "";
    
    @NonNull
    private String userName = "";
    
    @NonNull
    private String mail = "";
    
    @Exclude
    private String password = "";
    
    @NonNull
    private String profilepic = "";
    
    @NonNull
    private String status = "";
    
    @Nullable
    private String lastMessage;

    public Users() {
        // Required empty constructor for Firebase
    }

    public Users(@NonNull String userId, 
                @NonNull String userName, 
                @NonNull String mail, 
                @NonNull String password, 
                @NonNull String profilepic, 
                @NonNull String status) {
        this.userId = userId;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.profilepic = profilepic;
        this.status = status;
    }

    @NonNull
    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(@NonNull String profilepic) {
        this.profilepic = profilepic;
    }

    @NonNull
    public String getMail() {
        return mail;
    }

    public void setMail(@NonNull String mail) {
        this.mail = mail;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @Nullable
    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(@Nullable String lastMessage) {
        this.lastMessage = lastMessage;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }
}