package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("1", "VOUCHER", paymentData);
        assertEquals("1", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("1", "VOUCHER", "SUCCESS", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        Payment payment = new Payment("1", "VOUCHER", "REJECTED", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1", "VOUCHER", "MOO", paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1", "MOO", paymentData);
        });
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("1", "VOUCHER", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("1", "VOUCHER", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MOO"));
    }
}