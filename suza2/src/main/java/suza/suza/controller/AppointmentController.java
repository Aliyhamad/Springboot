package suza.suza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suza.suza.model.Appointment;
import suza.suza.service.AppointmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v7/appointment")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

        @Autowired
        private AppointmentService appointmentService;

        @PostMapping
        public Appointment post(@RequestBody Appointment appointment) {
                return appointmentService.post(appointment);
        }

        @GetMapping
        public List<Appointment> getAppointment() {
                return appointmentService.getAppointment();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Appointment> getById(@PathVariable Integer id) {
                Optional<Appointment> appointment = appointmentService.getById(id);
                return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public void deleteById(@PathVariable Integer id) {
                appointmentService.deleteById(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Appointment> updateAppointment(@PathVariable int id, @RequestBody Appointment appointmentDetails) {
                Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
                if (updatedAppointment != null) {
                        return ResponseEntity.ok(updatedAppointment);
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

}
