package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentByVoucher extends Payment {
    public PaymentByVoucher(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    public PaymentByVoucher(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, status, paymentData);
    }

    public void setPaymentData(Map<String, String> paymentData) {

    }
}