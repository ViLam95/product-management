package com.example.productmanagementthymleaf.controller;

import com.example.productmanagementthymleaf.model.Product;
import com.example.productmanagementthymleaf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    public IProductService productService;


    @GetMapping
    public ModelAndView findAll() {
        List<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Product product) {
        productService.create(product);
        return "redirect:/products";
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Product product) {
        productService.update(product);
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.remove(id);
        return "redirect:/products";
    }
    @PostMapping("/search")
    public ModelAndView search(@RequestParam String searchName) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("products", productService.searchByName(searchName));
        return modelAndView;
    }
}
