package com.example;

public class PasswordResetService {
    // Simulated database/cache for OTPs
    private final String validOtp = "123456";

    public boolean resetPassword(String username, String otp, String newPassword) {
        if (username == null || username.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Username and new password cannot be empty.");
        }
        
        if (validOtp.equals(otp)) {
            System.out.println("Password successfully reset for user: " + username);
            return true;
        } else {
            System.out.println("Invalid OTP. Reset failed.");
            return false;
        }
    }
}