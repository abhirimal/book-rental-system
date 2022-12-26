package com.abhiyan.bookrentalsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "role_name")
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Member> memberList;

}
