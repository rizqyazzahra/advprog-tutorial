package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentByTransfer extends Payment {
    public PaymentByTransfer(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    public PaymentByTransfer(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, status, paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {

    }
}