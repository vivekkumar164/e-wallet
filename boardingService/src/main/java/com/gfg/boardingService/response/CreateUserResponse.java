package com.gfg.boardingService.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserResponse extends Response {
    public String name;
    public String email;
    public String mobileNo;
}
