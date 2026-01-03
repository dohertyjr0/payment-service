package ie.atu.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "account-service", url = "http://localhost:8081")
public interface AccountServiceClient {

    @GetMapping("/accountlist")
    List<AccountDTO> getAllAccounts();

    @PutMapping("/accountlist/updateAccount")
    AccountDTO updateAccount(@RequestBody AccountDTO accountDTO);
}




