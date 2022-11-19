package com.ks.controller;

import com.ks.common.ApplicationConstants;
import com.ks.dto.User;
import com.ks.service.UserService;
import com.ks.vto.GenericResponse;
import com.ks.vto.LoginRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginCtrl {

    @Autowired
    private UserService userService;

    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@RequestBody LoginRequest request, HttpSession session) {
        GenericResponse response = new GenericResponse();
        User user = userService.getUserByEmail(request.getUsername());
        if (null != user && StringUtils.equals(request.getPassword(), user.getPassword())) {
            session.setAttribute(ApplicationConstants.USER_INFO, user);
            response.setContent(user);
            return new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
        }
        response.setErrorMsg("Credentials are invalid");
        return new ResponseEntity<GenericResponse>(response, HttpStatus.BAD_REQUEST);
    }
}
