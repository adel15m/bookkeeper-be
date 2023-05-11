package com.adel.expenses.service;

import com.adel.expenses.entity.Student;
import com.adel.expenses.entity.StudentDao;
import com.adel.expenses.service.dto.RequestDto;
import com.adel.expenses.service.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

//    public ResponseDto test1(RequestDto requestDto) {
//        String name = requestDto.getComplex().getVal();
//        int mark =  requestDto.getX() / requestDto.getY();
//        return new ResponseDto(name, mark);
//    }

    public StudentDto save(RequestDto requestDto) {
        Student saved = studentDao
                .save(Student
                        .builder()
                        .name(requestDto.getName())
                        .year(requestDto.getYear())
                        .active(requestDto.getActive())
                        .build());
        return saved.dto();
    }

    public List<StudentDto> getAll() {
        List<StudentDto> list = new ArrayList<>();
        List<Student> all = studentDao.findAll();
        for (Student student : all) {
            list.add(student.dto());
            student.setYear(2000);
            studentDao.save(student);
        }
        return list;
    }


}
