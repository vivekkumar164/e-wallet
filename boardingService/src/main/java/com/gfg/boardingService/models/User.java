package com.gfg.boardingService.models;

import com.gfg.boardingService.models.enums.UserIdentifier;
import com.gfg.boardingService.models.enums.UserStatus;
import com.gfg.boardingService.models.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String password;
    @Column(nullable = false,unique = true)
    String email;
    @Column(nullable = false,unique = true)
    String mobileNo;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserIdentifier userIdentifier;

    @Column(nullable = false,unique = true)
    String userIdentifierValue;

    @Enumerated(EnumType.STRING)
    UserType userType;

    @Enumerated(EnumType.STRING)
    UserStatus userStatus;

    @CreationTimestamp
    Date createdOn;
    @UpdateTimestamp
    Date updatedOn;
}
