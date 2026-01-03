package ie.atu.paymentservice.service;

import ie.atu.paymentservice.Payment;
import ie.atu.paymentservice.client.AccountDTO;
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
        List<AccountDTO> allAccounts = accountServiceClient.getAllAccounts();

        AccountDTO senderAccount = findAccountByName(allAccounts, payment.getSender());
        if (senderAccount == null) {
            throw new IllegalArgumentException("Sender account does not exist: " + payment.getSender());
        }

        AccountDTO receiverAccount = findAccountByName(allAccounts, payment.getReceiver());
        if (receiverAccount == null) {
            throw new IllegalArgumentException("Receiver account does not exist: " + payment.getReceiver());
        }

        if (senderAccount.getBalance() < payment.getAmount()) {
            throw new IllegalArgumentException("Insufficient funds in account: " + payment.getSender());
        }

        senderAccount.setBalance(senderAccount.getBalance() - payment.getAmount());
        receiverAccount.setBalance(receiverAccount.getBalance() + payment.getAmount());

        accountServiceClient.updateAccount(senderAccount);
        accountServiceClient.updateAccount(receiverAccount);

        return paymentRepository.save(payment);
    }

    private AccountDTO findAccountByName(List<AccountDTO> accounts, String accountName) {
        return accounts.stream()
                .filter(acc -> accountName.equals(acc.getAccountName()))
                .findFirst()
                .orElse(null);
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