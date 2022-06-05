package com.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Schema(description = "username интересуещего пользователя")
@Validated
public class SubscribeDTO {
    @NotNull
    @Schema(description = "username", required = true)
    @JsonProperty("username")
    String username;

}
