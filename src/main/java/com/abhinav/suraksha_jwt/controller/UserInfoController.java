package com.abhinav.suraksha_jwt.controller;

import com.abhinav.suraksha_jwt.dto.AuthRequest;
import com.abhinav.suraksha_jwt.model.UserInfo;
import com.abhinav.suraksha_jwt.service.JwtService;
import com.abhinav.suraksha_jwt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserInfoController {
    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @GetMapping("/common/welcome")
    @ResponseStatus(HttpStatus.OK)
    public String welcome(){
        return "This message will be displayed to all.";
    }


    @PostMapping("/common/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signup(@RequestBody UserInfo userInfo){
        return service.createUser(userInfo);
    }

    @GetMapping("/for_admins")
    @ResponseStatus(HttpStatus.OK)
    public String forAdmins(){
        return "This message will be displayed to Admins only.";
    }

    @GetMapping("/for_users")
    @ResponseStatus(HttpStatus.OK)
    public String forUsers(){
        return "This message will be displayed to Admins and Users";
    }


    @PostMapping("/common/token")
    public String authenticateAndGetToken(@RequestBody AuthRequest authReq){
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authReq.getUsername());
        }else {
            throw new UsernameNotFoundException("Either username or password is incorrect");
        }

    }

}
