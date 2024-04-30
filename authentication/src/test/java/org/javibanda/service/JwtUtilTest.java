package org.javibanda.service;

import lombok.val;
import org.javibanda.model.enums.TokenType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class JwtUtilTest {

    @InjectMocks
    private JwtUtil jwtUtil;

    @Before
    public void setUp() {
        setPrivateField(jwtUtil, "secret", "secret_number_longer_extension_for_testing");
        setPrivateField(jwtUtil, "expiration", "360");
        jwtUtil.initKey();
    }

    @Test
    public void should_getClaimsAccess() {
        val userId = "1234";
        val token = jwtUtil.generate(userId, "USER", TokenType.ACCESS);

        val claim = jwtUtil.getClaims(token);

        assertEquals(claim.get("id"), userId);
    }

    @Test
    public void should_getClaimsRefresh() {
        val userId = "1234";
        val token = jwtUtil.generate(userId, "USER", TokenType.REFRESH);

        val claim = jwtUtil.getClaims(token);

        assertEquals(claim.get("id"), userId);
    }

    private static void setPrivateField(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set private field: " + fieldName, e);
        }
    }
}