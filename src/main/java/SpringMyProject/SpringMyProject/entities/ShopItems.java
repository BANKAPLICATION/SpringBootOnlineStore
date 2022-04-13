package SpringMyProject.SpringMyProject.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_items")
public class ShopItems {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "images")
    private String images;

    @ManyToOne(fetch = FetchType.EAGER)
    private Countries country;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Categories> categories;


}
