package ie.atu.paymentservice;

import ie.atu.paymentservice.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataLoader implements CommandLineRunner {
    private final PaymentRepository paymentRepository;

    public PaymentDataLoader(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void run(String... args) {
        Payment payment1 = new Payment(300.0, "Matthew", "John");
        Payment payment2 = new Payment(200.0, "Jack", "John");
        Payment payment3 = new Payment(400.0, "David", "John");

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
        paymentRepository.save(payment3);

        System.out.println("Sample PAYMENT data loaded - Transactions between accounts");
    }
}
