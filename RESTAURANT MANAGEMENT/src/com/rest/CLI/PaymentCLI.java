
package com.rest.CLI;

import com.rest.Payment.*;
import com.rest.models.Payment;
import com.rest.services.PaymentService;
import java.util.List;
import java.util.Scanner;

public class PaymentCLI {

    private final PaymentService paymentService;
    private final Scanner scanner = new Scanner(System.in);

    public PaymentCLI() {
        paymentService = new PaymentService();
    }

    public void start() {
        int choice = -1;

        do {
            System.out.println("\n=== Restaurant Payment System ===");
            System.out.println("1. Make Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. View Payment By ID");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        makePayment();
                        break;

                    case 2:
                        viewAllPayments();
                        break;

                    case 3:
                        viewPaymentById();
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);
    }

    private void makePayment() {
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.println("Select payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        System.out.print("Enter choice: ");

        int methodChoice = Integer.parseInt(scanner.nextLine());

        PaymentMethod method;

        switch (methodChoice) {
            case 1:
                method = new CashPayment();
                break;
            case 2:
                method = new CardPayment();
                break;
            case 3:
                method = new UpiPayment();
                break;
            default:
                throw new IllegalArgumentException(
                    "Invalid payment method."
                );
        }

        Payment payment = paymentService.processPayment(method, amount);
        System.out.println("Payment successful!");
        System.out.println(payment);
    }

    private void viewAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();

        if (payments.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }

        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    private void viewPaymentById() {
        System.out.print("Enter payment ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Payment payment = paymentService.getPaymentById(id);

        if (payment == null) {
            System.out.println("Payment not found.");
        } else {
            System.out.println(payment);
        }
    }
}
