package org.example.mae_back.controllers;


import org.example.mae_back.dto.UserDto;
import org.example.mae_back.requests.LoginRequest;
import org.example.mae_back.requests.RegistrationRequest;
import org.example.mae_back.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/v1")
@RestController

public class UserController {


    private UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;

    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> Register(@RequestBody RegistrationRequest registrationRequest) {
        return userServices.registerService(registrationRequest);
    }

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody LoginRequest loginRequest) {
        return userServices.loginService(loginRequest);
    }
    @GetMapping("/status/{id}")
    public ResponseEntity CheckStatus(@PathVariable Integer id) {
        return userServices.checkStatus(id);
    }


}
/*
@Controller
@RequestMapping(path = "api/v1")

class  WebController{
    private JavaMailSender mailSender;
    private UserRepository userRepository;

    public WebController(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }
    @GetMapping("/reset-password")
    public String  ResetPassword(*/
/*@RequestBody String email*//*
) {

        */
/*  return userServices.resetPassword(email);*//*

        return "reset-password";
    }
    @PostMapping("/submit-form")
      public ResponseEntity<String> submitForm(@RequestParam("email") String email) {
       Optional<User>  user= userRepository.findByEmail(email);
        if(user.isPresent()){
            return ResponseEntity.status(200).body("An email with password reset instructions has been sent to your email address.");

        }
        return ResponseEntity.status(400).body("Not found");

    }
}*/
