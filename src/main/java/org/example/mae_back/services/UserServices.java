package org.example.mae_back.services;

import org.example.mae_back.data.RoleRepository;
import org.example.mae_back.data.UserRepository;
import org.example.mae_back.dto.UserDto;
import org.example.mae_back.dto_convertor.UserConvertor;
import org.example.mae_back.models.Role;
import org.example.mae_back.models.User;
import org.example.mae_back.requests.LoginRequest;
import org.example.mae_back.requests.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServices  {



    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private JavaMailSender mailSender;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServices(JavaMailSender mailSender, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this. roleRepository= roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailSender = mailSender;
    }



    public ResponseEntity registerService(RegistrationRequest request) {

        Optional<User> checkUser = this.userRepository.findByEmail(request.getEmail());

        if (checkUser.isPresent()) {




            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "L'e-mail est deja utilisé ");
            return ResponseEntity.status(404).body(responseMap);
        }

        User user = new User();
        String encodedPassword;
Role role=roleRepository.getById(2L);
        encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setPhone_number(request.getPhone_number());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLast_name(request.getLast_name());
        user.setRegion(request.getRegion());
        user.setGender(request.getGender());
        user.setToken(request.getToken());
        user.setStatus(false);
        user.setRole(role);
        userRepository.save(user);
        return ResponseEntity.status(200).body(UserConvertor.userToDto(user));

    }
    public ResponseEntity loginService(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()) {
            if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
                user.get().setToken(loginRequest.getToken());
                userRepository.save(user.get());
                return ResponseEntity.status(200).body(UserConvertor.userToDto(user.get()));

            } else {

                Map<String, String> responseMap = new HashMap<>();
                responseMap.put("message", "Mot de passe incorrecte");
                return ResponseEntity.status(400).body(responseMap);

            }
        }
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Aucun utilisateur avec ces données");
        return ResponseEntity.status(401).body(responseMap);
    }


   /* public ResponseEntity resetPasswordService(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {

            if (user.get().getPassword() != null) {
                ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
                resetPasswordRequest.setUser(user.get());
                Random rand = new Random();
                Long code = rand.nextLong(9000) + 1000;
                resetPasswordRequest.setCode(code);
                resetPasswordRequestRepository.save(resetPasswordRequest);
                Map<String, String> responseMap = new HashMap<>();
                responseMap.put("message", "Un e-mail de vérification a été envoyé à votre adresse e-mail");

                sendEmail(email, "Réinitialiser le mot de passe", "Bonjour , voici le code pour réinitialiser votre mot de passe " + code);
                return ResponseEntity.status(200).body(responseMap);
            } else {
                Map<String, String> responseMap = new HashMap<>();
                responseMap.put("message", "Vous ne pouvez pas réinitialiser le mot de passe créé avec " + user.get().getType());
                return ResponseEntity.status(401).body(responseMap);
            }
        }

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Utilisateur non trouvé");
        return ResponseEntity.status(402).body(responseMap);
    }
*/

  /*  public ResponseEntity checkOtp(Long code) {
        Optional<ResetPasswordRequest> resetPasswordRequest = resetPasswordRequestRepository.getResetPasswordRequestByCode(code);
        if (resetPasswordRequest.isPresent() && Objects.equals(resetPasswordRequest.get().getCode(), code)) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "done");
            return ResponseEntity.status(200).body(responseMap);
        } else {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "Le code est invalide");
            return ResponseEntity.status(404).body(responseMap);
        }
    }
*/
   /* public ResponseEntity changePassword(ChangePasswordRequest changePasswordRequest) {
        Optional<User> user = userRepository.findByEmail(changePasswordRequest.getEmail());
        String encodedPassword;
        encodedPassword = bCryptPasswordEncoder.encode(changePasswordRequest.getPassword());

        user.get().setPassword(encodedPassword);
        userRepository.save(user.get());
        resetPasswordRequestRepository.deleteByCode(changePasswordRequest.getCode());
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "done");
        return ResponseEntity.status(200).body(responseMap);


    }
*/
    /*public ResponseEntity loginService(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()) {

            if (user.get().getPassword() != null ) {
                if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
                    user.get().setToken(loginRequest.getToken());
                    userRepository.save(user.get());
                    return ResponseEntity.status(200).body(UserConvertor.userToDto(user.get()));

                } else {

                    Map<String, String> responseMap = new HashMap<>();
                    responseMap.put("error", "Mot de passe incorrecte");
                    return ResponseEntity.status(400).body(responseMap);

                }

            } else if (user.get().getPassword() == null && Objects.equals(user.get().getType(), loginRequest.getType())) {
                user.get().setToken(loginRequest.getToken());
                userRepository.save(user.get());
                return ResponseEntity.status(200).body(UserConvertor.userToDto(user.get()));
            }
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "Aucun utilisateur avec ces données");
            return ResponseEntity.status(401).body(responseMap);
        }
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error", "Aucun utilisateur avec ces données");
        return ResponseEntity.status(401).body(responseMap);
    }*/

    public void sendEmail(String toEmail,
                          String subject,
                          String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fathi3ayari333@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }

    public List<UserDto> getAllUsers(Integer id) {
        List<User> users=userRepository.findAll();
        users.removeIf(user -> user.getId().equals(id));

       return users.stream().map(UserConvertor::userToDto).toList();
    }

    public ResponseEntity checkStatus(Integer id) {
        Optional<User> user=userRepository.findById(id);
        Map responseMap = new HashMap<>();
        if(user.isPresent()){
            if(user.get().getStatus()){
                responseMap.put("status","active");
            }else {responseMap.put("status","inactive");}
        }else {
            responseMap.put("status","not found");
        }

        return ResponseEntity.status(200).body(responseMap);
    }
}
