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

    // ADD THIS: This is the entry point for Docker/Kubernetes
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Password Reset Service is now RUNNING!");
        System.out.println("Waiting for requests...");
        System.out.println("========================================");

        // This keeps the container alive so you can see it as "Running" in Kubernetes
        try {
            Object lock = new Object();
            synchronized (lock) {
                while (true) {
                    lock.wait();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Service interrupted.");
        }
    }
}