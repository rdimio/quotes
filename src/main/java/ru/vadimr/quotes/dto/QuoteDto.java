package ru.vadimr.quotes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuoteDto {
    private String title;
    private String login;
    private int score;
    private int number;
}
