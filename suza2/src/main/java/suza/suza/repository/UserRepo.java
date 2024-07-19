package suza.suza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import suza.suza.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
@Query(value = "select * from users ur where ur.username = :username", nativeQuery = true)
Optional<User> findByUsername( String username);

    Optional<User> findByEmail(String patientEmail);
}
