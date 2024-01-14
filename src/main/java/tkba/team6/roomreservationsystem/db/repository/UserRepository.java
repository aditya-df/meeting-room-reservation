package tkba.team6.roomreservationsystem.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import tkba.team6.roomreservationsystem.db.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
    Users save(@Nullable Users Users);

    Optional<Users> findById(long id);

    List<@NonNull Users> findAll();

    Users findByUsernameAndPassword(String username, String password);
}