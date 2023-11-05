package com.utcn.sd_project.service;

import com.utcn.sd_project.model.UserDevice;
import com.utcn.sd_project.repository.UserDeviceRepository;
import com.utcn.sd_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDeviceService {
    @Autowired
    private UserDeviceRepository userDeviceRepository;

    public void linkDevice(Long userID, Long deviceID) {
        userDeviceRepository.save(new UserDevice(userID, deviceID));
        Map<String, Long> response = new HashMap<>();
        response.put("userID", userID);
        response.put("deviceID", deviceID);

        String updateDeviceMicroservice = "http://localhost:8081/link-device";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(updateDeviceMicroservice, response);
    }
}
