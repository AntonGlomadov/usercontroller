package com.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Тело запроса на подписку")
@Validated
public class FindDTO {
    @NotNull
    @Schema(description = "username part", required = true)
    @JsonProperty("usernamePart")
    String usernamePart;
}


