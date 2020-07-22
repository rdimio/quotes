package ru.vadimr.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vadimr.quotes.entities.Quote;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    @Query("SELECT count(q) FROM Quote q ")
    int countRows();

    Quote findOneById(Integer id);

    @Query(value = "select  c, u.login, q.title from quotes q join (select s.quote_id, count(s.user_id) as c from score s group by s.quote_id order by c desc limit 10)ss on (q.id = ss.quote_id) join users u on (u.id = q.user_id);", nativeQuery = true)
    List<String> getTopTenQuotes();
}
