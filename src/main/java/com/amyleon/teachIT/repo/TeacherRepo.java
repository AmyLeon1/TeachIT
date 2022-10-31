package com.amyleon.teachIT.repo;


import com.amyleon.teachIT.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//pass in Teacher class as this is the repo we are making
// with data type of primary key
@Repository
@Transactional(readOnly = true)
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);
}

