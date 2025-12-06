package com.group.learn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "guests")
public class Guest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String phone;
    private String email;
    private String passportNumber;
    private String nationality;
    private String gender;
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
