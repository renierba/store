/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rbn.store.product.service;

import com.rbn.store.product.entity.Category;
import com.rbn.store.product.entity.Product;
import com.rbn.store.product.repository.ProductRepository;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository prepository;

    @Override
    public List<Product> listAllProduct() {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        return prepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        return prepository.findById(id).orElseThrow();
    }

    @Override
    public Product createProduct(Product p) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        
        p.setStatus("CREATED");
        p.setFecha(new Date());
        return prepository.save(p);
    }

    @Override
    public Product updateProduct(Product p) {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        Product prod = getProduct(p.getId());
        if(prod == null)
            return null;
        else{
    //        prod.setCategory(p.getCategory());
            prod.setDescripcion(p.getDescripcion());
            prod.setName(p.getName());
            prod.setPrecio(p.getPrecio());
            return prepository.save(prod);
        }
    }

    @Override
    public Product deleteProduct(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        Product prod = getProduct(id);
        if(prod == null || prod.getStatus()== "DELETED")
            return null;
        else{
            prod.setStatus("DELETED");
            return prepository.save(prod);
        }
    }

    @Override
    public List<Product> findByCategory(Category cat) {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        return prepository.findByCategory(cat);
    }

    @Override
    public Product updateStock(Long id, int newstock) {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        Product prod = getProduct(id);
        if(prod == null)
            return null;
        else{
            int stock = prod.getStock() + newstock;
            prod.setStock(stock);
            return prepository.save(prod);
        }
    }

    

}
