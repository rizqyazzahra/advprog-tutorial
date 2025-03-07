package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VoucherCodeTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testEmptyPaymentData() {
        PaymentByVoucher payment = new PaymentByVoucher("1", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(this.paymentData));
    }

    @Test
    void testSetPaymentDataValidVoucher() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        PaymentByVoucher payment = new PaymentByVoucher("1", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataInvalidVoucherShortLength() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC56");
        PaymentByVoucher payment = new PaymentByVoucher("1", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataInvalidVoucherNoPrefix() {
        this.paymentData.put("voucherCode", "1234ABC5678");
        PaymentByVoucher payment = new PaymentByVoucher("1", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataInvalidVoucherNoEightNumericalCharacters() {
        this.paymentData.put("voucherCode", "ESHOPABCDEFGH");
        PaymentByVoucher payment = new PaymentByVoucher("1", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}