package com.example.userservice.dto;

import com.example.userservice.db.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
@Schema(description = "Инфа о пользователе")
@Validated
public class UserDTO {

    @NotNull
    @Schema(description = "usename", required = true)
    @JsonProperty("username")
    private String username;

    @Null
    @Schema(description = "Статус 100 символов", required = true)
    @JsonProperty("status")
    private String status;

    @Null
    @Schema(description = "Путь до фото")
    @JsonProperty("photo")
    private String photo;

    @Null
    @Schema(description = "дата рождения", required = true)
    @JsonProperty("birthdate")
    private LocalDateTime birthDate;

    @NotNull
    @Schema(description = "гендер", required = true)
    @JsonProperty("sex")
    private Gender gender;

    @NotNull
    @Schema(description = "Имя", required = true)
    @JsonProperty("firstname")
    private String firstName;

    @NotNull
    @Schema(description = "Фамилия/Матчество", required = true)
    @JsonProperty("lastname")
    private String lastName;
}
