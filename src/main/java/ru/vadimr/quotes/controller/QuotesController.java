package ru.vadimr.quotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vadimr.quotes.dto.QuoteDto;
import ru.vadimr.quotes.entities.Quote;
import ru.vadimr.quotes.entities.User;
import ru.vadimr.quotes.service.QuoteService;
import java.util.List;

@Controller
public class QuotesController {

    private QuoteService quoteService;

    @Autowired
    public void setQuoteService(QuoteService quoteService){
        this.quoteService = quoteService;
    }

    @GetMapping("/")
    public String topPage(Model model) {
        Quote quote = quoteService.getRandomQuote();
        model.addAttribute("quote", quote);
        List<QuoteDto> quotes = quoteService.getTopTenQuotes();
        model.addAttribute("quotes", quotes);
        return "index";
    }


    @GetMapping("/last")
    public String lastPage() {return "last";}
}
