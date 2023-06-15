package com.example.productmanagementthymleaf.service;

import com.example.productmanagementthymleaf.DAO.ProductDAO;
import com.example.productmanagementthymleaf.model.Product;

import java.util.List;

public class ProductService implements IProductService{


    @Override
    public List<Product> findAll() {
       return ProductDAO.getInstance().findAll();
    }

    @Override
    public void create(Product product) {
        ProductDAO.getInstance().createNewProduct(product);
    }

    @Override
    public Product findById(int id) {
      return ProductDAO.getInstance().findById(id);
    }

    @Override
    public void update(Product product) {
       ProductDAO.getInstance().updateProduct(product);
    }

    @Override
    public void remove(int id) {
        ProductDAO.getInstance().deleteById(id);
    }

    @Override
    public List<Product> searchByName(String searchName) {
      return ProductDAO.getInstance().searchByName(searchName);
    }
}
