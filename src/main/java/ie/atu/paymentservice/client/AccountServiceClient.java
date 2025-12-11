package ie.atu.paymentservice.client;

import ie.atu.paymentservice.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accountlist", url = "http://localhost:8081")
public interface AccountServiceClient {

    @GetMapping("/accounts/exists/{accountName}")
    Boolean checkAccountExists(@PathVariable("accountName") String accountName);

    @PutMapping("/accounts/update-balance")
    void updateBalances(@RequestBody Payment payment);
}
