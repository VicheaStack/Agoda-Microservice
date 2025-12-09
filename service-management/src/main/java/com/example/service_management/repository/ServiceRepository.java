package com.example.service_management.repository;

import com.example.service_management.entity.ServiceManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceManagement, Long> {
}
