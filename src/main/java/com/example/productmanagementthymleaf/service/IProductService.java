package com.example.productmanagementthymleaf.service;

import com.example.productmanagementthymleaf.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void create(Product product);
    Product findById (int id);
    void update (Product product);
    void remove(int id);
    List<Product> searchByName(String searchName);

}
