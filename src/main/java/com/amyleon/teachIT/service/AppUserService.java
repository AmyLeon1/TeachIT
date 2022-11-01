package com.amyleon.teachIT.service;

import com.amyleon.teachIT.model.Teacher;
import com.amyleon.teachIT.repo.TeacherRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService
{
    private final static String USER_NOT_FOUND_MSG = "user with email %s not  found";
    //reference to repository
    private final TeacherRepo teacherRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUserService(TeacherRepo teacherRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.teacherRepo = teacherRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return teacherRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(Teacher appUser){

            //check if email already exists before signing them uo
           boolean userExists = teacherRepo
                   .findByEmail(appUser.getEmail())
                    .isPresent();

           if(userExists){
               throw new IllegalStateException("email already taken");

           }

           //encode password
           String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

           appUser.setPassword(encodedPassword);

           //save to database
           teacherRepo.save(appUser);


        return "it works";
    }
}
