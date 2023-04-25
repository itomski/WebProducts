package de.lubowiecki.webproducts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class AppConfig {

    @Bean // Wird vom Spring-Container verwaltet und kann mit Autowired verfügbar gemacht werden
    //@RequestScope // Für jede Anfrage ein neues Objekt
    @SessionScope // Jeder Besucher hat für die Dauer seines Besuchs ein Objekt
    //@ApplicationScope // Für alle Besucher und solange die App auf dem Server läuft gibt es ein Objekt
    public LoginService loginService() {
        return new LoginService();
    }
}
