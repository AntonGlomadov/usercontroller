package com.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Schema(description = "Инфа о подписках/подписчиках")
@Validated
public class ShortUserInfoDTO {
    @NotNull
    @Schema(description = "usename", required = true)
    @JsonProperty("username")
    private String username;
    @Null
    @Schema(description = "Путь до фото")
    @JsonProperty("photo")
    private String photo;
    @NotNull
    @Schema(description = "Имя", required = true)
    @JsonProperty("firstname")
    private String firstName;
    @NotNull
    @Schema(description = "Фамилия/Матчество", required = true)
    @JsonProperty("lastname")
    private String lastName;
}
