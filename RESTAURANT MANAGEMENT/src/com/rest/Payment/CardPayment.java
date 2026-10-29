
package com.rest.Payment;

public class CardPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using card.");
    }

    @Override
    public String getMethodName() {
        return "Card";
    }
}
