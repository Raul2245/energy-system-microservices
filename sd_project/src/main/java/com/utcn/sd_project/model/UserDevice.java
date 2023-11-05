package com.utcn.sd_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_device")
public class UserDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userID;

    private Long deviceID;

    public UserDevice(Long userID, Long deviceID) {
        this.userID = userID;
        this.deviceID = deviceID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Long deviceID) {
        this.deviceID = deviceID;
    }
}
