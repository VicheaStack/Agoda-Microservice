package com.example.staff_service.repository;

import com.example.staff_service.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    boolean existsStaffByEmail(String email);
    List<Staff> findByEmail(String email);
    boolean existsStaffByCitizenshipBefore(String citizenshipBefore);
}
