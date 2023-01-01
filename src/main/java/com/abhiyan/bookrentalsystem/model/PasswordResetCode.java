package com.abhiyan.bookrentalsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reset_code")
public class PasswordResetCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Integer userId;

    String resetCode;
}
