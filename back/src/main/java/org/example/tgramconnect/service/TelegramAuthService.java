package org.example.tgramconnect.service;

import org.example.tgramconnect.model.User;
import org.example.tgramconnect.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Service
public class TelegramAuthService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${telegram.bot.token}")
    private String botToken;

    public TelegramAuthService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public User authenticateUser(Map<String, String> telegramData) {
        String telegramId = telegramData.get("id");

        // Проверка достоверности данных через Telegram API
        if (!isTelegramDataValid(telegramData)) {
            throw new IllegalArgumentException("Telegram data is not valid.");
        }

        User user = userRepository.findByTelegramId(telegramId);
        if (user == null) {
            user = new User();
            user.setTelegramId(telegramId);
            user.setFirstName(telegramData.get("first_name"));
            user.setLastName(telegramData.get("last_name"));
            user.setPhoneNumber(telegramData.get("phone_number"));
            user.setUsername(telegramData.get("username"));
            user.setLanguageCode(telegramData.get("language_code"));
            user.setProfilePhoto(telegramData.get("photo_url"));
            userRepository.save(user);
        }

        return user;
    }

    private boolean isTelegramDataValid(Map<String, String> telegramData) {
        String checkHash = telegramData.get("hash");
        String url = "https://api.telegram.org/bot" + botToken + "/getMe";

        // Отправляем запрос в Telegram API
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {

            return true;
        } else {
            return false;
        }
    }
}
