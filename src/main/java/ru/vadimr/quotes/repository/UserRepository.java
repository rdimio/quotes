package ru.vadimr.quotes.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vadimr.quotes.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findOneByLogin(String login);

    @Query(value = "select q.title from quotes q join score s on (q.id = s.quote_id) where s.user_id = :id", nativeQuery = true)
    List<String> getVotes(@Param("id")Integer id);

    User findOneByEmail(String email);
}