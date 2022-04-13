package SpringMyProject.SpringMyProject.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "comment")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;

    @Column(name = "item_id")
    private Long item_id;

    @Column(name = "postDate")
    private Timestamp postDate;

}
