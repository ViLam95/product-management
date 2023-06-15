package com.example.productmanagementthymleaf.DAO;

import com.example.productmanagementthymleaf.connection.MyConnection;
import com.example.productmanagementthymleaf.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection = MyConnection.getConnection();
    private static ProductDAO productDAO;
    public static ProductDAO getInstance() {
        if (productDAO == null) {
            productDAO = new ProductDAO();
        }
        return productDAO;
    }
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        String query = "select * from product;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                productList.add(new Product(id,name,price,quantity,image));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
    public Product findById (int id) {
        Product product = null;
        String query = "select * from product where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                product = new Product(id,name,price,quantity,image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public void createNewProduct(Product product) {
        String query = "insert into product(name,price,quantity,image) values (?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getImage());
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateProduct(Product product) {
        String query = "update product set name = ?, price = ?, quantity = ?, image = ? where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getImage());
            preparedStatement.setInt(5,product.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteById (int id) {
        String query = "DELETE FROM product WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Product> searchByName(String searchName) {
        List<Product> productList = new ArrayList<>();
        String query = "select * from product where name like ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchName + "%");
            convertResultSetToList(productList, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
    private void convertResultSetToList(List<Product> productList, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            String image = resultSet.getString("image");
            Product product = new Product(id,name,price,quantity,image);
            productList.add(product);
        }
    }
}
