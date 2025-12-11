package ie.atu.paymentservice;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}