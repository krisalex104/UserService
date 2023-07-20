package com.hms.user.service.entities;


import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_uid")
    private String userUid;
    @Column(name = "name" , length = 50)
    @NonNull
    private String name;

    @Column(name = "email" , length = 50)
    @NonNull
    private String email;

    @Column(name = "about" , length = 150)
    private String about;

    @Column(name = "active_status")
    private Integer activeStatus;

}
