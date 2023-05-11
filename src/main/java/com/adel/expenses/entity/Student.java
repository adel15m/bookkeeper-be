package com.adel.expenses.entity;


import com.adel.expenses.service.dto.StudentDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "student")
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "active")
    private Boolean active;

    public StudentDto dto(){
        return StudentDto
                .builder()
                .active(this.getActive())
                .id(this.getId())
                .name(this.getName())
                .year(this.getYear())
                .age(2023-year)
                .build();


    }

}
