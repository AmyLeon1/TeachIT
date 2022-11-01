package com.amyleon.teachIT.registration;

import com.amyleon.teachIT.model.AppUserRole;
import com.amyleon.teachIT.model.Teacher;
import com.amyleon.teachIT.service.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public RegistrationService(AppUserService appUserService, EmailValidator emailValidator) {
        this.appUserService = appUserService;
        this.emailValidator = emailValidator;
    }

    public String register(RegistrationRequest request) {
       boolean isValidEmail = emailValidator.test(request.getEmail());
       if(!isValidEmail){
           // TODO: not good way to handle exception
           throw new IllegalStateException("email not valid");
       }
        return appUserService.signUpUser(
                new Teacher(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getUserName(),
                        request.getPassword(),
                       AppUserRole.USER
                )
        );
    }
}

