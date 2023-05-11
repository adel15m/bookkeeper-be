package com.adel.expenses.controller;

import com.adel.expenses.service.StudentService;
import com.adel.expenses.service.dto.RequestDto;
import com.adel.expenses.service.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;

//    @PostMapping(value = "/test1")
//    public String test1() {
//        return new Date().toString();
//    }
//
//    @PostMapping(value = "/test2")
//    public ResponseDto test2(@Valid @RequestBody RequestDto requestDto) {
//        return testService.test1(requestDto);
//    }


    @PostMapping(value = "/save")
    public StudentDto saveStudent(@Valid @RequestBody RequestDto requestDto) {
        return studentService.save(requestDto);
    }
    @GetMapping(value = "/getall")
    public List<StudentDto> getall() {
        return studentService.getAll();
    }
}
