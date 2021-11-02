/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rbn.store.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author rbatista
 */
@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String descripcion;
    private int stock;
    private double precio;
    private String status;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "categoryid")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;
    
}
