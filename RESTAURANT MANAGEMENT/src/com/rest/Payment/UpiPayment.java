
package com.rest.Payment;

public class UpiPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI.");
    }

    @Override
    public String getMethodName() {
        return "UPI";
    }
}
