package com.abhiyan.bookrentalsystem.model;

import com.abhiyan.bookrentalsystem.enums.AccountState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name="member",uniqueConstraints = {
        @UniqueConstraint(name = "email",columnNames = "email")
})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;

    private String name;

    private String mobileNumber;

    private String address;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_state")
    private AccountState accountState;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;
}
