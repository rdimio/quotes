package ru.vadimr.quotes.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vadimr.quotes.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findOneByLogin(String login);

    User findOneByEmail(String email);

}