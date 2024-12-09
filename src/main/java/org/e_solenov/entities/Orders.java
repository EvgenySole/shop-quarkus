package org.e_solenov.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity(name = "Orders")
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ClientID")
    private Clients clientID;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "OrderID")
    private List<OrderDetails> orderDetailsList;

    private Date orderDate;

    private Date shipDate;

    @ManyToOne
    @JoinColumn(name = "StatusID")
    private Statuses statusID;

    private double orderPrice;
}
