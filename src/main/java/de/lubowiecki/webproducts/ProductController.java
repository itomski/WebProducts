package de.lubowiecki.webproducts;

import de.lubowiecki.webproducts.model.Product;
import de.lubowiecki.webproducts.model.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private LoginService loginService;

    // HTTP: GET @GetMapping, POST @PostMapping, PUT @PutMapping, DELETE @DeleteMapping
    // @RequestMapping: erlaubt alle Request-Methoden

    @GetMapping("/")
    public String index(Model uiModel) {
        if(!loginService.isLoggedIn()) return "redirect:/login";
        uiModel.addAttribute("products", repository.findAll()); // Macht die List für das Template verfügbar
        uiModel.addAttribute("active", "list");
        return "basic"; // verwendet basic.html aus resources/templates
    }

    @GetMapping("/new")
    public String newForm(Model uiModel) {
        if(!loginService.isLoggedIn()) return "redirect:/login";
        uiModel.addAttribute("active", "new");
        uiModel.addAttribute("product", new Product());
        return "form";
    }

    @PostMapping("/save")
    public String save(@Valid Product product, BindingResult result, Model uiModel) {

        if(!loginService.isLoggedIn()) return "redirect:/login";

        if(result.hasErrors()) {
            return "form";
        }

        if(product.getId() == 0) {
            product.setCreatedAt(LocalDate.now());
        }
        repository.save(product);
        return "redirect:/";
    }

    @GetMapping("/settings")
    public String settings(Model uiModel) {

        if(!loginService.isLoggedIn()) return "redirect:/login";

        uiModel.addAttribute("active", "settings");
        return "settings";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model uiModel) {

        if(!loginService.isLoggedIn()) return "redirect:/login";
        Optional<Product> opt = repository.findById(id);
        if(opt.isPresent()) {
            repository.delete(opt.get());
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model uiModel) {

        if(!loginService.isLoggedIn()) return "redirect:/login";

        Optional<Product> opt = repository.findById(id);
        if(opt.isPresent()) {
            uiModel.addAttribute("product", opt.get());
        }
        else {
            uiModel.addAttribute("product", new Product());
        }
        return "form";
    }
}