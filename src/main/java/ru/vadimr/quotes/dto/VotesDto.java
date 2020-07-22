package ru.vadimr.quotes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VotesDto {
    private List<String> list;
    private int count;
}
