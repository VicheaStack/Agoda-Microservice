package com.example.staff_service.serviceImpl;

import com.example.staff_service.config.ResourceNotFoundException;
import com.example.staff_service.entity.Staff;
import com.example.staff_service.repository.StaffRepository;
import com.example.staff_service.service.StaffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    private static final Logger logger = LogManager.getLogger(StaffServiceImpl.class);
    private final StaffRepository staffRepository;
   // private final PasswordEncoder passwordEncoder;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
     //   this.passwordEncoder = passwordEncoder;
        logger.info("StaffServiceImpl initialized");
    }

    @Override
    public Staff hiring(Staff staff) {
        logger.info("Starting hiring process for staff with email: {}", staff.getEmail());

        // Validate email
        if (staff.getEmail() == null || staff.getEmail().isBlank()) {
            logger.error("Hiring failed: Email is null or blank");
            throw new ResourceNotFoundException("Email is required for hiring");
        }

        logger.debug("Email validation passed: {}", staff.getEmail());

        // Validate username
        if (staff.getUsername() == null || staff.getUsername().isBlank()) {
            logger.error("Hiring failed: Username is null or blank");
            throw new ResourceNotFoundException("Username is required for hiring");
        }

        logger.debug("Username validation passed: {}", staff.getUsername());

        // Check if email already exists
        if (staffRepository.existsStaffByEmail(staff.getEmail())) {
            logger.warn("Hiring failed: Email already exists in database - {}", staff.getEmail());
            throw new ResourceNotFoundException("Staff already exists with email: " + staff.getEmail());
        }

        logger.debug("Email uniqueness check passed for: {}", staff.getEmail());

        // Set hire date if not provided
        if (staff.getHireDate() == null) {
            staff.setHireDate(LocalDate.now());
            logger.debug("Set default hire date to today");
        } else {
            logger.debug("Using provided hire date: {}", staff.getHireDate());
        }

        // Save staff (global exception handler will catch any errors)
        Staff saved = staffRepository.save(staff);
        logger.info("Staff hired successfully! ID: {}, Email: {}, Username: {}",
                saved.getId(), saved.getEmail(), saved.getUsername());
        logger.debug("Saved staff details: {}", saved);

        return saved;
    }

    @Override
    @Transactional
    public Staff promote(Long id, Staff staff) {
        logger.info("Starting promotion process for staff ID: {}", id);

        Staff update = staffRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Promotion failed: Staff not found with ID: {}", id);
                    return new ResourceNotFoundException("Can't find User by id: " + id);
                });

        logger.debug("Found staff to promote: ID={}, Name={} {}, Current Dept={}",
                update.getId(), update.getFirstName(), update.getLastName(), update.getDepartment());

        // Log changes before update
        logChanges("firstName", update.getFirstName(), staff.getFirstName());
        logChanges("lastName", update.getLastName(), staff.getLastName());
        logChanges("department", update.getDepartment(), staff.getDepartment());
        logChanges("salary", update.getSalary(), staff.getSalary());

        // Update fields
        update.setFirstName(staff.getFirstName());
        update.setLastName(staff.getLastName());
        update.setDateOfBirth(staff.getDateOfBirth());
        update.setDepartment(staff.getDepartment());
        update.setEmail(staff.getEmail());
        update.setHireDate(staff.getHireDate());
        update.setCitizenship(staff.getCitizenship());
        update.setPhone(staff.getPhone());
        update.setPassword(staff.getPassword());
        update.setUsername(staff.getUsername());
        update.setSalary(staff.getSalary());

        // Save updates (global exception handler will catch any errors)
        Staff promoted = staffRepository.save(update);
        logger.info("Staff promoted successfully! ID: {}, New Dept: {}, New Salary: {}",
                promoted.getId(), promoted.getDepartment(), promoted.getSalary());
        logger.debug("Promoted staff details: {}", promoted);

        return promoted;
    }

    @Override
    public List<Staff> FindStaffById(Long id) {
        logger.info("Looking for staff by ID: {}", id);

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Staff not found with ID: {}", id);
                    return new ResourceNotFoundException("User not exist with ID: " + id);
                });

        logger.info("Staff found: ID={}, Name={} {}, Email={}",
                staff.getId(), staff.getFirstName(), staff.getLastName(), staff.getEmail());
        logger.debug("Staff details: {}", staff);

        return List.of(staff);
    }

    @Override
    @Transactional
    public void teminateById(Long id) {
        logger.info("Starting termination process for staff ID: {}", id);

        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            logger.info("Staff terminated successfully! ID: {}", id);
        } else {
            logger.warn("Termination failed: Staff not found with ID: {}", id);
            throw new ResourceNotFoundException("Staff not found with ID: " + id);
        }
    }

    // Helper method to log field changes
    private void logChanges(String fieldName, Object oldValue, Object newValue) {
        if (oldValue == null && newValue == null) {
            return;
        }

        if (oldValue == null || newValue == null || !oldValue.equals(newValue)) {
            logger.debug("Field '{}' changed: {} -> {}", fieldName, oldValue, newValue);
        }
    }
}