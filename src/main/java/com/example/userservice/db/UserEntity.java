package com.example.userservice.db;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "USERS")
public class UserEntity {

    @Setter(AccessLevel.PROTECTED)
    private @Id
    String login;

    @Setter(AccessLevel.PROTECTED)
    private String photoUrl;

    @Setter(AccessLevel.PROTECTED)
    private LocalDateTime birthDate;

    @Setter(AccessLevel.PROTECTED)
    private String status;

    @Setter(AccessLevel.PROTECTED)
    private String firstName;

    @Setter(AccessLevel.PROTECTED)
    private String lastname;

    @Setter(AccessLevel.PROTECTED)
    private Gender gender;
}
