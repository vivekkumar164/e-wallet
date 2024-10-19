package com.gfg.boardingService.services;

import com.gfg.CommonConstants;
import com.gfg.boardingService.models.User;
import com.gfg.boardingService.models.enums.UserStatus;
import com.gfg.boardingService.models.enums.UserType;
import com.gfg.boardingService.repositories.UserRepository;
import com.gfg.boardingService.request.CreateUserRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;



    public User createUserInDB(CreateUserRequest createUserRequest) {
        User user = new User();
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());
        user.setPassword(encodedPassword);
        user.setUserType(UserType.NORMAL);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setName(createUserRequest.getName());
        user.setEmail(createUserRequest.getEmail());
        user.setMobileNo(createUserRequest.getMobileNo());
        user.setUserIdentifier(createUserRequest.getUserIdentifier());
        user.setUserIdentifierValue(createUserRequest.getUserIdentifierValue());

        try {
            userRepository.save(user);
        }
        catch (Exception e){
            System.out.println("Error creating user in UserService"+e.getMessage());
            return  null;
        }

        // if the user is successfully onboarded, send the data to kafka

        JSONObject userDetails = new JSONObject();
        userDetails.put(CommonConstants.USER_NAME,user.getName());
        userDetails.put(CommonConstants.USER_EMAIL,user.getEmail());
        userDetails.put(CommonConstants.USER_MOBILE,user.getMobileNo());
        userDetails.put(CommonConstants.USER_IDENTIFIER,user.getUserIdentifier());
        userDetails.put(CommonConstants.USER_IDENTIFIER_VALUE,user.getUserIdentifierValue());

        kafkaTemplate.send(CommonConstants.USER_TOPIC_NAME,userDetails.toString());

        System.out.println("Data sent to kakfa");
        return user;



    }

    public String validateUser(String mobile){
        User user = userRepository.findByMobileNo(mobile);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.USER_MOBILE,user.getMobileNo());
        jsonObject.put(CommonConstants.USER_EMAIL,user.getEmail());
        jsonObject.put(CommonConstants.USER_PASSWORD,user.getPassword());

        return jsonObject.toString();
    }
}
