package com.lms.dao;

import com.lms.model.User;
import com.lms.util.PasswordUtil;
import com.lms.demo.DemoDataProvider;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * User Data Access Object - Demo Mode Only (No Database)
 */
public class UserDAO {

    private List<User> users;

    public UserDAO() {
        this.users = DemoDataProvider.getUsers();
    }

    /**
     * Register a new user
     */
    public boolean registerUser(User user) {
        try {
            int newId = users.size() + 1;
            user.setUserId(newId);
            user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            users.add(user);
            System.out.println("✓ User registered successfully!");
            return true;
        } catch (Exception e) {
            System.err.println("Registration error - " + e.getMessage());
            return false;
        }
    }

    /**
     * Login user
     */
    public User loginUser(String username, String password) {
        try {
            String hashedPassword = PasswordUtil.hashPassword(password);
            
            for (User user : users) {
                if (user.getUsername().equals(username) && 
                    user.getPassword().equals(hashedPassword)) {
                    System.out.println("✓ Login successful for: " + username);
                    return user;
                }
            }
            
            System.out.println("✗ Login failed for: " + username);
            return null;
        } catch (Exception e) {
            System.err.println("Login error - " + e.getMessage());
            return null;
        }
    }

    /**
     * Get user by ID
     */
    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    /**
     * Get user by username
     */
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Update user profile
     */
    public boolean updateUserProfile(User user) {
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserId() == user.getUserId()) {
                    users.set(i, user);
                    System.out.println("✓ Profile updated successfully!");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.err.println("Profile update error - " + e.getMessage());
            return false;
        }
    }

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Check if username exists
     */
    public boolean usernameExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if email exists
     */
    public boolean emailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update user (alias for updateUserProfile)
     */
    public boolean updateUser(User user) {
        return updateUserProfile(user);
    }

    /**
     * Update user profile image
     */
    public boolean updateProfileImage(int userId, String imagePath) {
        try {
            User user = getUserById(userId);
            if (user != null) {
                user.setProfileImage(imagePath);
                System.out.println("Profile image updated for user: " + userId);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

