package ru.vadimr.quotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vadimr.quotes.dto.QuoteDto;
import ru.vadimr.quotes.entities.Quote;
import ru.vadimr.quotes.repository.QuoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {
    private QuoteRepository quoteRepository;

    @Autowired
    public void setQuoteRepository(QuoteRepository quoteRepository){
        this.quoteRepository = quoteRepository;
    }

    public Quote getRandomQuote() {
        int count = quoteRepository.countRows();
        Random random = new Random();
        Integer number = random.nextInt(count);
        Quote q = quoteRepository.findOneById(number);
        return q;
    }

    public List<QuoteDto> getTopTenQuotes() {
        List<String> response = quoteRepository.getTopTenQuotes();
        List<QuoteDto> quotes = new ArrayList<>();
        int i = 1;
        for(String s : response){
            String[] str = s.split(",");
            StringBuilder builder = new StringBuilder();
            for (int j = 2; j < str.length; j++) {
                builder.append(str[j]);
            }
            QuoteDto dto = new QuoteDto();
            dto.setTitle(builder.toString());
            dto.setLogin(str[1]);
            dto.setScore(Integer.valueOf(str[0]));
            dto.setNumber(i);
            i++;
            quotes.add(dto);
        }
        return quotes;
    }
}
