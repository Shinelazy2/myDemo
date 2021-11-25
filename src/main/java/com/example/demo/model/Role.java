package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment? 설정
    private Long id;

    private String name;


    //join 양방향 매핑
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
