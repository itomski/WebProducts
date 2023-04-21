package de.lubowiecki.webproducts;

import de.lubowiecki.webproducts.model.Product;
import de.lubowiecki.webproducts.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/")
    public String index(Model uiModel) {
        uiModel.addAttribute("products", repository.findAll()); // Macht die List für das Template verfügbar
        return "basic"; // verwendet basic.html aus resources/templates
    }
}