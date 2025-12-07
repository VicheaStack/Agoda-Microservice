package com.example.staff_service.service;

import com.example.staff_service.entity.Staff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {

    Staff hiring(Staff staff);
    Staff promote(Long id, Staff staff);
    List<Staff> FindStaffById(Long id);
    void teminateById(Long id);
}
