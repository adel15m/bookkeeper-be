package com.adel.expenses.service.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

    private Long id;
    private String name;
    private Integer year;
    private Boolean active;
    private Integer age;

}
