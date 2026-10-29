
package com.rest.services;

import com.rest.models.Payment;
import com.rest.repository.PaymentRepository;
import com.rest.Payment.PaymentMethod;
import java.util.List;

public class PaymentService {

    private final PaymentRepository repository;
    private int nextPaymentId = 1;

    public PaymentService() {
        repository = new PaymentRepository();
    }

    public Payment processPayment(PaymentMethod method, double amount) {

        if (method == null) {
            throw new IllegalArgumentException(
                "Payment method cannot be null"
            );
        }

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be positive"
            );
        }

        method.pay(amount);

        Payment payment = new Payment(nextPaymentId++,
            amount,
            method.getMethodName(),
            "SUCCESS"
        );

        repository.save(payment);
        return payment;
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public Payment getPaymentById(int id) {
        return repository.findById(id);
    }
}
