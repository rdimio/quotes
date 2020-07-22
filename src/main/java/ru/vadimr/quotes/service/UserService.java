package ru.vadimr.quotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vadimr.quotes.dto.UserDto;
import ru.vadimr.quotes.entities.User;
import ru.vadimr.quotes.repository.UserRepository;
import java.util.List;


@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User findByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    public User findByEmail(String email) { return userRepository.findOneByEmail(email);}

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findOneByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Incorrect login or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), null);
    }

    public List<User> findAll() {return (List<User>) userRepository.findAll();}

    @Transactional
    public void delete(Integer id) { userRepository.deleteById(id);
    }

    @Transactional
    public void save(User user) { userRepository.save(user);
    }

    @Transactional
    public void registrationUser(UserDto userDto) {
        User user = new User(userDto.getLogin(),passwordEncoder.encode(userDto.getPassword()),userDto.getEmail());
        userRepository.save(user);
    }

}
