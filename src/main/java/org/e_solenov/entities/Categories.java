package org.e_solenov.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Categories")
@Data
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String categoryName;

    private String description;
}
