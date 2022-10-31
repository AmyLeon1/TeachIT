package com.amyleon.teachIT.service;



import com.amyleon.teachIT.model.Teacher;
import com.amyleon.teachIT.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
//@Transactional
public class TeacherService {

    private final TeacherRepo teacherRepo;

    //pass in teacherRepo as parameter
    @Autowired
    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public Teacher addTeacher(Teacher teacher){
        teacher.setId(UUID.randomUUID().toString());
        return teacherRepo.save(teacher);
    }

    //fetch teachers from database
    public List<Teacher> getTeachers(){
        return teacherRepo.findAll();
    }
}

