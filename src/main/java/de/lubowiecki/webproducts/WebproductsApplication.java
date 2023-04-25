package de.lubowiecki.webproducts;

import de.lubowiecki.webproducts.model.Product;
import de.lubowiecki.webproducts.model.ProductRepository;
import de.lubowiecki.webproducts.model.User;
import de.lubowiecki.webproducts.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebproductsApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Value("${db.reset}")
	private boolean dbReset; // Wert wird aus der application.properties gelesen

	public static void main(String[] args) {
		SpringApplication.run(WebproductsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { // Wir 1x beim Start der Anwendung ausgeführt

		if(dbReset) {
			productRepository.deleteAll(); // Löscht alte Daten

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

			productRepository.saveAll(products);

			userRepository.deleteAll();

			User user = new User();
			user.setEmail("peter@parker.de");
			user.setPassword("geheim#123");

			userRepository.save(user);
		}
	}
}
