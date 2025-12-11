package ie.atu.paymentservice;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Amount must be positive")
    private double amount;

    @NotBlank(message = "Sender cannot be blank")
    private String sender;

    @NotBlank(message = "Receiver cannot be blank")
    private String receiver;

    public Payment(double amount, String sender, String receiver) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
    }
}
