package db.entity;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean role;
}

interface UserRepository extends Repository<users, Long> {
  users save(users users);

  Optional<users> findById(long id);
}