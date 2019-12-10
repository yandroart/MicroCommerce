package com.ecommerce.MicroCommerce.controller;

import com.ecommerce.MicroCommerce.dao.ProductDao;
import com.ecommerce.MicroCommerce.exceptions.ProduitIntrouvableException;
import com.ecommerce.MicroCommerce.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Api(description = "Gestion des produits")
@RestController()
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductDao  productDao;

    @GetMapping
    public ResponseEntity<?>listProduits(){
        return new ResponseEntity<>(productDao.findAll(),HttpStatus.OK);
    }
    //Produits/{id}
    @ApiOperation(value = "Récupère le produit selon ID.")
    @GetMapping(value = "/{id}")
    public Product afficherUnProduit(@PathVariable int id) throws ProduitIntrouvableException {

        Product product = productDao.findById(id);

        if (product==null) throw  new ProduitIntrouvableException("Le produit avec Id: "+id+" n'existe pas.");
        return product;

    }
    @PostMapping
    public ResponseEntity<Void> ajoutProduct(@Valid @RequestBody Product product){
        Product product1 = productDao.save(product);
        if (product==null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product1.getId())
                .toUri();
                        return ResponseEntity.created(location).build();
    }
    @GetMapping(value = "/test/{prixLimit}")
    public Optional<List<Product>> testDeRequetes(@PathVariable int prixLimit){
        return productDao.chercherUnProduitCher(prixLimit);
    }
}
