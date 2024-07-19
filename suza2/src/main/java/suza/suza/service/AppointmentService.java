package suza.suza.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suza.suza.model.Appointment;
import suza.suza.repository.AppointmentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Transactional
    public Appointment post(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    public List<Appointment> getAppointment() {
        return appointmentRepo.findAll();
    }

    public Optional<Appointment> getById(Integer id) {
        return appointmentRepo.findById(id);
    }

    public void deleteById(Integer id) {
        appointmentRepo.deleteById(id);
    }

    @Transactional
    public Appointment updateAppointment(int id, Appointment appointmentDetails) {
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setApp_date(appointmentDetails.getApp_date());
            appointment.setApp_time(appointmentDetails.getApp_time());
            appointment.setDisease(appointmentDetails.getDisease());
            appointment.setSelection_doctor(appointmentDetails.getSelection_doctor());
            return appointmentRepo.save(appointment);
        } else {
            return null;
        }
    }
}
