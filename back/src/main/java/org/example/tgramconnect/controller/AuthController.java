package org.example.tgramconnect.controller;

import org.example.tgramconnect.model.User;
import org.example.tgramconnect.service.TelegramAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final TelegramAuthService telegramAuthService;

    public AuthController(TelegramAuthService telegramAuthService) {
        this.telegramAuthService = telegramAuthService;
    }

    @PostMapping("/telegram")
    public User authenticate(@RequestBody Map<String, String> telegramData) {
        return telegramAuthService.authenticateUser(telegramData);
    }
}
