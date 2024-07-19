package suza.suza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suza.suza.model.Patient;
@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Patient findByUsername(String username);
}
