package com.nsmstravels.nsms_backend.repository;

// 1. THIS IS THE CRUCIAL IMPORT! It must point to your model.
import com.nsmstravels.nsms_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// 2. You MUST have <User, Long> right here:
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}