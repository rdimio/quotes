package ru.vadimr.quotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vadimr.quotes.dto.QuoteDto;
import ru.vadimr.quotes.dto.UserDto;
import ru.vadimr.quotes.dto.VotesDto;
import ru.vadimr.quotes.entities.Quote;
import ru.vadimr.quotes.entities.User;
import ru.vadimr.quotes.service.QuoteService;
import ru.vadimr.quotes.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    private QuoteService quoteService;

    @Autowired
    public void setQuoteService(QuoteService quoteService){
        this.quoteService = quoteService;
    }


    @PostMapping("/")
    public String start(@ModelAttribute(name = "user") User user, Model model){
        User newUser = userService.findByLogin(user.getLogin(), user.getPassword());
        if(newUser != null)
            model.addAttribute("newUser", newUser);
        else model.addAttribute("error", "wrong login or password");
        VotesDto votes = userService.getVotes(newUser.getId());
        model.addAttribute("votes", votes);
        Quote quote = quoteService.getRandomQuote();
        model.addAttribute("quote", quote);
        List<QuoteDto> quotes = quoteService.getTopTenQuotes();
        model.addAttribute("quotes", quotes);
        return "index";
    }

    @GetMapping("/registration")
    public String goToRegistration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute(name = "userDto") UserDto user,
                               Model model){
        if(userService.findOneByLogin(user.getLogin())!=null){
            model.addAttribute("user", user);
            model.addAttribute("error", "this login is already used");
            return "registration";
        }
        if(userService.findByEmail(user.getEmail())!=null)   {
            model.addAttribute("user", user);
            model.addAttribute("error", "this email is already used");
            return "registration";
        }
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("user", user);
            model.addAttribute("error", "password and confirmPassword don't match");
            return "registration";
        }
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model, @RequestParam(required = false) String login){
        User user = userService.findOneByLogin(login);
        model.addAttribute("user", user);
        return "profile";
    }
}
