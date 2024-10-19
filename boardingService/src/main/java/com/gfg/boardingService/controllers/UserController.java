package com.gfg.boardingService.controllers;

import com.gfg.boardingService.models.User;
import com.gfg.boardingService.request.CreateUserRequest;
import com.gfg.boardingService.response.CreateUserResponse;
import com.gfg.boardingService.response.Response;
import com.gfg.boardingService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create/user")
    public ResponseEntity<Response> onBoardUser(@RequestBody CreateUserRequest createUserRequest){
    if(createUserRequest == null){
        Response response = new Response();
        response.setCode("022");
        response.setMessage("Provide user information");
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    if(createUserRequest.getEmail().length() == 0 || createUserRequest.getMobileNo().length() == 0){
        Response response = new Response();
        response.setCode("023");
        response.setMessage("Provide email address and mobile no correctly");
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    if(createUserRequest.getPassword() == null){
        Response response = new Response();
        response.setCode("024");
        response.setMessage("Provide password");
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    User user = userService.createUserInDB(createUserRequest);
        CreateUserResponse createUserResponse = new CreateUserResponse();
    if(user == null){
        createUserResponse.setCode("01");
        createUserResponse.setMessage("User creation failed");
    }else{
        createUserResponse.setCode("01");
        createUserResponse.setMessage("User onBoard operation successful");
        createUserResponse.setName(user.getName());
        createUserResponse.setEmail(user.getEmail());
        createUserResponse.setMobileNo(user.getMobileNo());
    }
    return new ResponseEntity<>(createUserResponse,HttpStatus.CREATED);
    }
    @GetMapping("/validate/user")
    public String validateUser(@RequestParam("mobileNo") String mobileNo){
        System.out.println(mobileNo);
        return userService.validateUser(mobileNo);
    }
}
