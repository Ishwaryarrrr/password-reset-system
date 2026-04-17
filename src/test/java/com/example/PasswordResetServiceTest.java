package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordResetServiceTest {
    
    PasswordResetService service = new PasswordResetService();

    @Test
    public void testValidOtp() {
        assertTrue(service.resetPassword("user1", "123456", "newPass123"));
    }

    @Test
    public void testInvalidOtp() {
        assertFalse(service.resetPassword("user1", "000000", "newPass123"));
    }

    @Test
    public void testEmptyUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.resetPassword("", "123456", "newPass123");
        });
    }
}