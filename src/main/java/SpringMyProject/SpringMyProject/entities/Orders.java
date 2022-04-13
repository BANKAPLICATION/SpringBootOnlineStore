package SpringMyProject.SpringMyProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private int amount;
    @Column(name = "cardnumber")
    private String cardnumber;

    @Column(name = "expiration")
    private String expiration;
    
    
    @Column(name = "cvv")
    private String cvv;



    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "item_id")
    private Long item_id;

}
