package gq.cader.realfakestoreserver.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@Entity
public
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToOne
    @EqualsAndHashCode.Exclude
    private ShoppingCart shoppingCart;

    @ManyToMany
    @Column(name = "ADDRESSES")
    @EqualsAndHashCode.Exclude
    private List<Address> addresses;

    //TODO Figure out what annotation needed to force unique or one to one relationship
    @Email
    private String email;

    @ElementCollection(targetClass = Order.class)
    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<Order> orders;
}