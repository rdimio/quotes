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

    @Query(value = "select  c,u.login,q.title from quotes.quotes q join (select quote_id, count(user_id) as c from quotes.score group by quote_id order by c desc limit 10)s on (q.id = s.quote_id) join quotes.users u on (u.id = q.user_id);", nativeQuery = true)
    List<String> getTopTenQuotes();
}
