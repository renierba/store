/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rbn.store.product.controller;

import com.rbn.store.product.entity.Category;
import com.rbn.store.product.entity.Product;
import com.rbn.store.product.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rbatista
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {
    
    @Autowired
    private ProductService pservice;
    
    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId){
        
        List<Product> prod = new ArrayList();
        if(categoryId == null){
            prod = pservice.listAllProduct();
            if(prod.isEmpty())
                return ResponseEntity.noContent().build();
        }     
        else{
            prod = pservice.findByCategory(Category.builder().id(categoryId).build());
            if(prod.isEmpty())
                return ResponseEntity.notFound().build();       
        }
        
           return ResponseEntity.ok(prod);
    }

    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product prod = pservice.getProduct(id);
        if(prod == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prod);
    }
    
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product p){
        
        Product prod = pservice.createProduct(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        
        Product prod = pservice.deleteProduct(id);
        if(prod == null)
            return ResponseEntity.notFound().build();
        else{
            return ResponseEntity.ok(prod);
    }
    }
    
    @PutMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable  Long id ,@RequestParam(name = "quantity", required = true) int quantity){
        Product product = pservice.updateStock(id, quantity);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    
    
}
