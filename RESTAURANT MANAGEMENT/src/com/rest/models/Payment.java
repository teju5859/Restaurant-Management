
package com.rest.models;

public class Payment {

    private int paymentId;
    private double amount;
    private String method;
    private String status;

    public Payment(int paymentId, double amount,
                   String method, String status) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Payment{id=" + paymentId
                + ", amount=" + String.format("%.2f", amount)
                + ", method=" + method
                + ", status=" + status + "}";
    }
}
