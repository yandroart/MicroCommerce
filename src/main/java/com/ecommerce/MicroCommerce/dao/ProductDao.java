package com.ecommerce.MicroCommerce.dao;

import com.ecommerce.MicroCommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    Product findById(int id);
    Optional<List<Product>> findByPrixGreaterThan(int prixLimit);
    @Query("SELECT p FROM Product p WHERE p.prix > :prixLimit")
    Optional<List<Product>> chercherUnProduitCher(@Param("prixLimit") int prix);

}
