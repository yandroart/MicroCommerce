package com.ecommerce.MicroCommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;
    @Length(min = 3, max = 50, message = "Le nombre de caractère doit être inférieur où égal à 20.")
    private String nom;
    private int prix;
    //ne pas afficher
    private int prixAchat;


}
