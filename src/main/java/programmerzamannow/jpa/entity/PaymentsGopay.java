package programmerzamannow.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payments_gopay")
@Data
public class PaymentsGopay extends Payment {

    @Column(name = "gopay_id")
    private String gopayId;
}
