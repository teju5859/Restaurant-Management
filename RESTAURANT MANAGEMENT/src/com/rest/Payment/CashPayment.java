
package com.rest.Payment;

public class CashPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using cash.");
    }

    @Override
    public String getMethodName() {
        return "Cash";
    }
}
