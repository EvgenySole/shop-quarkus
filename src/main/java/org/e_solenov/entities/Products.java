package org.e_solenov.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "Products")
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Categories categoryID;

    @ManyToOne
    @JoinColumn(name = "ModelNumber")
    private Models modelNumber;

    private String productImage;

    private double price;

    private String description;

    private Date productionDate;

    private int quantity;
}
