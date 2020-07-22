package ru.vadimr.quotes.entities;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String password;

    @Column
    private String login;

    @Column
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "score",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
    private List<Quote> votes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Quote> quotes;


    public User(String password, String login, String email) {
        this.password = password;
        this.login = login;
        this.email = email;
    }
}
