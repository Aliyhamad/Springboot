package suza.suza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suza.suza.model.Appointment;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
}
