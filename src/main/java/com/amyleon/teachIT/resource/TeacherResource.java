package com.amyleon.teachIT.resource;


import com.amyleon.teachIT.model.Teacher;
import com.amyleon.teachIT.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/teacher") //base url
public class TeacherResource {

    //bring in service so we can use it
    private final TeacherService teacherService;

    //inject service into constructor so we can autowire amd use it
    @Autowired
    public TeacherResource(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping
    public List<Teacher> hello() {
        return teacherService.getTeachers();
    }

    @PostMapping("/add")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        //pass in teacher that was received from the request
        Teacher newTeacher = teacherService.addTeacher(teacher);
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

}

