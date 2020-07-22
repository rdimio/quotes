package ru.vadimr.quotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vadimr.quotes.dto.UserDto;
import ru.vadimr.quotes.dto.VotesDto;
import ru.vadimr.quotes.entities.Quote;
import ru.vadimr.quotes.entities.User;
import ru.vadimr.quotes.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLogin(String login, String password) {
        User user = userRepository.findOneByLogin(login);
        if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        else return null;
    }

    public VotesDto getVotes(Integer id) {
        List<String> list = userRepository.getVotes(id);
        List<String> str = new ArrayList<>();
        int i = 0;
        for(String s : list){
            str.add(s.substring(0, 7));
            i++;
        }
        VotesDto votesDto = new VotesDto();
        votesDto.setList(str);
        votesDto.setCount(i);
        return votesDto;
    }

    public User findByEmail(String email){
        return userRepository.findOneByEmail(email);
    }

    public User findOneByLogin(String login){
        return userRepository.findOneByLogin(login);
    }

    public void save(UserDto userDto){
        String hash = bCryptPasswordEncoder.encode(userDto.getPassword());
        User user = new User(hash, userDto.getLogin(), userDto.getEmail());
        userRepository.save(user);
    }
}
