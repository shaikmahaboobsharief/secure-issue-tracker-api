package com.sharief.secure_issue_tracker.user.service;

import com.sharief.secure_issue_tracker.exception.ResourceNotFoundException;
import com.sharief.secure_issue_tracker.user.dto.UpdateUserRoleRequest;
import com.sharief.secure_issue_tracker.user.dto.UserResponse;
import com.sharief.secure_issue_tracker.user.entity.User;
import com.sharief.secure_issue_tracker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        log.debug("Fetching all users");

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponse getUserById(Long id) {
        log.debug("Fetching user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found with id: " + id);
                });

        return mapToResponse(user);
    }

    public UserResponse getCurrentUser(String email) {
        log.debug("Fetching current user profile for email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Current user not found with email: {}", email);
                    return new ResourceNotFoundException("User not found with email: " + email);
                });

        return mapToResponse(user);
    }

    public UserResponse updateUserRole(Long id, UpdateUserRoleRequest request) {
        log.info("Updating role for user id: {} to {}", id, request.getRole());

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Role update failed. User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found with id: " + id);
                });

        user.setRole(request.getRole());
        User updatedUser = userRepository.save(user);

        log.info("User role updated successfully for user id: {}", id);

        return mapToResponse(updatedUser);
    }

    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Delete failed. User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found with id: " + id);
                });

        userRepository.delete(user);

        log.info("User deleted successfully with id: {}", id);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
