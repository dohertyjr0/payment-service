package ie.atu.paymentservice.service;

import ie.atu.paymentservice.Payment;
import ie.atu.paymentservice.client.AccountServiceClient;
import ie.atu.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountServiceClient accountServiceClient;

    public PaymentService(PaymentRepository paymentRepository, AccountServiceClient accountServiceClient) {
        this.paymentRepository = paymentRepository;
        this.accountServiceClient = accountServiceClient;
    }

    public Payment create(Payment payment) {
        Boolean senderExists = accountServiceClient.checkAccountExists(payment.getSender());
        Boolean receiverExists = accountServiceClient.checkAccountExists(payment.getReceiver());

        if (Boolean.FALSE.equals(senderExists) || Boolean.FALSE.equals(receiverExists)) {
            throw new IllegalArgumentException("Payment failed: Sender or Receiver account does not exist.");
        }

        accountServiceClient.updateBalances(payment);

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

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}