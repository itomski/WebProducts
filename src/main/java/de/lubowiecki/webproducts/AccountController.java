package de.lubowiecki.webproducts;

import de.lubowiecki.webproducts.model.User;
import de.lubowiecki.webproducts.model.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    //public String loginForm(HttpServletRequest req, HttpServletResponse resp, Model uiModel) {
    public String loginForm(Model uiModel) {

        /*
        // Cookies die an den Browser gesender werden sollen, müssen an das Perspons gebunden werden
        Cookie cookie = new Cookie("xyz", "DasIstDasHausVonNikigraus");
        resp.addCookie(cookie);

        // Cookies die vom Browser kommen sind im Request enthalten
        for(Cookie c : req.getCookies()) {
            System.out.println(c.getName() + ": " + c.getValue());
        }
        */

        return "login-form";
    }

    @PostMapping("/login")
    public String login(String email, String password, Model uiModel) {

        Optional<User> opt = repository.findByEmail(email);
        if(opt.isPresent()) {
            User user = opt.get();
            if(user.getPassword().equals(password)) {
                loginService.logIn(user);
                return "redirect:/private";
            }
        }
        uiModel.addAttribute("err", true);
        return "login-form";
    }

    @GetMapping("/logout")
    public String logout(Model uiModel) {
        loginService.logOut();
        return "redirect:/login";
    }

    @GetMapping("/private")
    public String privatePage(Model uiModel) {
        if(!loginService.isLoggedIn()) return "redirect:/login";
        return "basic";
    }
}
