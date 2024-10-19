package com.gfg.boardingService.request;

import com.gfg.boardingService.models.User;
import com.gfg.boardingService.models.enums.UserIdentifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
    private String name;
    private String email;
    private String mobileNo;
    private String password;
    private UserIdentifier userIdentifier;
    private String userIdentifierValue;

    public  User toUser() {
        User user = User.builder()
                .name(this.name)
                .email(this.email)
                .mobileNo(this.mobileNo)
                .password(this.password)
                .userIdentifier(this.userIdentifier)
                .userIdentifierValue(this.userIdentifierValue)
                .build();

        return user;
    }
}
