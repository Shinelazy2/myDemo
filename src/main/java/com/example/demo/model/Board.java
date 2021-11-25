package com.example.demo.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment? 설정
    private Long id;

    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자이하 입니다.")
    private String title;
    private String content;
}
