package com.university.Harvard.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "lector")
@NoArgsConstructor
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Degree degree;

    private BigDecimal salary;
}
