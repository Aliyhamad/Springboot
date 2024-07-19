package suza.suza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suza.suza.model.Doctor;
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
}
