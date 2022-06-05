package com.example.userservice.rest.controller;

import com.example.userservice.db.UserService;
import com.example.userservice.dto.*;
import org.keycloak.KeycloakPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody UserDTO user) {
        userService.addUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity subscribe(@RequestBody SubscribeDTO user,Principal principal) {
        userService.subscribe(user,((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken().getPreferredUsername());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/unsubscribe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity unsubscribe(@RequestBody SubscribeDTO user,Principal principal) {
        userService.unsubscribe(user,((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken().getPreferredUsername());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/get/subscribes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShortUserInfoDTO> getAllSubscribes(Principal principal) {
        return userService.getAllSubscribes(((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken().getPreferredUsername());
    }
    @GetMapping(path = "/get/subscribers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShortUserInfoDTO> getAllSubscribers(Principal principal) {
        return userService.getAllSubscribers(((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken().getPreferredUsername());

    }

    @GetMapping(path = "/get/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserInfo(Principal principal) {
        return userService.getUserInfo(((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken().getPreferredUsername());
    }

    @PostMapping(path = "/get/user/other", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserandSubStatusDTO getOtherUserInfo(@RequestBody SubscribeDTO user, Principal principal) {
        return userService.getUserAndSubStatus(user.getUsername(),((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken().getPreferredUsername());
    }

    @PostMapping(path = "/finduser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShortUserInfoDTO> findUsers(@RequestBody FindDTO findPatern) {
        return userService.findUsers(findPatern);
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file , Principal principal) {
        String username =((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken().getPreferredUsername();
        String fileName = username+ UUID.randomUUID().toString().replace("-", "")+".jpg";
        try {
            file.transferTo( new File("/upload/" + fileName));
            userService.addPhoto(username, fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }
}
