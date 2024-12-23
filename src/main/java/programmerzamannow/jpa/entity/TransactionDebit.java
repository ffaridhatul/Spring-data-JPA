package programmerzamannow.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transactions_debit")
public class TransactionDebit extends Transaction {

    @Column(name = "debit_amount")
    private Long debitAmount;
}
