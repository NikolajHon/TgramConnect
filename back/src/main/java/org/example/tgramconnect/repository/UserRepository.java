package org.example.tgramconnect.repository;

import org.example.tgramconnect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByTelegramId(String telegramId);
}
