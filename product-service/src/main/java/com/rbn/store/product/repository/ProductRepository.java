/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rbn.store.product.repository;

import com.rbn.store.product.entity.Category;
import com.rbn.store.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rbatista
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
   public List<Product> findByCategory(Category category);
    
}
