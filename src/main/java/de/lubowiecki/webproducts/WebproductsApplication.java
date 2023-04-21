package de.lubowiecki.webproducts;

import de.lubowiecki.webproducts.model.Product;
import de.lubowiecki.webproducts.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebproductsApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(WebproductsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { // Wir 1x beim Start der Anwendung ausgeführt

		repository.deleteAll(); // Löscht alte Daten

		List<Product> products = new ArrayList<>();

		// Testdaten
		Product p = new Product();
		p.setName("Tasse");
		p.setQuantity(100);
		p.setPrice(3.99);
		p.setCreatedAt(LocalDate.now());
		products.add(p);

		p = new Product();
		p.setName("Mütze");
		p.setQuantity(10);
		p.setPrice(19.99);
		p.setCreatedAt(LocalDate.now());
		products.add(p);

		repository.saveAll(products);

	}
}
