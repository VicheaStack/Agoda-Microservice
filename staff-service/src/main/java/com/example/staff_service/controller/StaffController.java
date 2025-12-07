package com.example.staff_service.controller;

import com.example.staff_service.dto.StaffDTO;
import com.example.staff_service.entity.Staff;
import com.example.staff_service.mapper.StaffMappper;
import com.example.staff_service.service.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;
    private final StaffMappper staffMappper;

    public StaffController(StaffService staffService,
                           StaffMappper staffMappper){
        this.staffService = staffService;
        this.staffMappper = staffMappper;
    }

    @PostMapping
    public ResponseEntity<StaffDTO> hiring(@RequestBody StaffDTO dto){
        Staff entity = staffMappper.toEntity(dto);
        Staff hiring = staffService.hiring(entity);
        StaffDTO respose = staffMappper.toDTO(hiring);
        return ResponseEntity.ok(respose);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> Promote(@PathVariable Long id, @RequestBody StaffDTO dto){
        Staff entity = staffMappper.toEntity(dto);
        Staff promote = staffService.promote(id, entity);
        StaffDTO response = staffMappper.toDTO(promote);
        return ResponseEntity.ok(response);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        List<Staff> staff = staffService.FindStaffById(id);

        List<StaffDTO> collect = staff.stream()
                .map(staffMappper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> terminate(@PathVariable Long id){
        staffService.teminateById(id);
        return ResponseEntity.noContent().build();
    }
}
