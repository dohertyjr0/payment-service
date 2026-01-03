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
    private String sender;      // ← This stores the sender's ACCOUNT NAME

    @NotBlank(message = "Receiver cannot be blank")
    private String receiver;    // ← This stores the receiver's ACCOUNT NAME

    public Payment(double amount, String sender, String receiver) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
    }
}