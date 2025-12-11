package ie.atu.paymentservice.controller;

import ie.atu.paymentservice.Payment;
import ie.atu.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.findById(id);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Payment createPayment(@Valid @RequestBody Payment payment) {
        return paymentService.create(payment);
    }

    @PutMapping
    public Payment updatePayment(@Valid @RequestBody Payment payment) {
        return paymentService.update(payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable("id") Long id) {
        paymentService.delete(id);
    }
}