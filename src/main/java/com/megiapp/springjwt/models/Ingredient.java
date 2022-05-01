package com.megiapp.springjwt.models;

import com.megiapp.springjwt.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ingredients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float price;
    private String name;
    private Type type;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "taco_ingredients",
            joinColumns = @JoinColumn(name = "ingredients_id"),
            inverseJoinColumns = @JoinColumn(name = "taco_id"))
    private Set<Taco> tacos = new HashSet<>();

}