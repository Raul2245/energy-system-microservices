package com.utcn.sd_project.repository;

import com.utcn.sd_project.model.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {
}
