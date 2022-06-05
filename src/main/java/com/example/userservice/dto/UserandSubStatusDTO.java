package com.example.userservice.dto;

import com.example.userservice.db.SubscribeStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Информация о пользователе + статус подписки")
@Validated
public class UserandSubStatusDTO {
    @NotNull
    @Schema(description = "Информация о пользователе", required = true)
    @JsonProperty("user")
    private UserDTO user;

    @NotNull
    @Schema(description = "Информация о подписке", required = true)
    @JsonProperty("status")
    private SubscribeStatus subscibe;
}
