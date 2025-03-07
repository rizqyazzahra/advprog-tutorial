package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentByTransferTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testValidPaymentData() {
        this.paymentData.put("bankName", "Bank Adpro");
        this.paymentData.put("referenceCode", "123456789");
        PaymentByTransfer payment = new PaymentByTransfer("1", PaymentMethod.BANK_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetInvalidPaymentDataInvalidEmptyBankName() {
        this.paymentData.put("bankName", "");
        this.paymentData.put("referenceCode", "123456789");
        PaymentByTransfer payment = new PaymentByTransfer("1", PaymentMethod.BANK_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetInvalidPaymentDataEmptyReferenceCode() {
        this.paymentData.put("bankName", "Bank Adpro");
        this.paymentData.put("referenceCode", "");
        PaymentByTransfer payment = new PaymentByTransfer("1", PaymentMethod.BANK_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetInvalidPaymentDataEmptyPaymentData() {
        PaymentByTransfer payment = new PaymentByTransfer("1", PaymentMethod.BANK_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}