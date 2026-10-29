
package com.rest.Payment;

public interface PaymentMethod {
    void pay(double amount);

    String getMethodName();
}
