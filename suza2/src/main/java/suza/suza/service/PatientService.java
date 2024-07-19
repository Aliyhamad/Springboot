package suza.suza.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suza.suza.model.Patient;
import suza.suza.model.User;
import suza.suza.repository.PatientRepo;
import suza.suza.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    public PatientRepo patientRepo;

    @Autowired
    private UserRepo userRepo;

    //post
    public Patient post(Patient patient) {
        Patient savedPatient = patientRepo.save(patient);

        User user = new User();
        user.setPassword(savedPatient.getPassword());
        user.setEmail(savedPatient.getPatient_email());
        user.setUsername(savedPatient.getUsername());
        user.setRole("patient");
        userRepo.save(user);

        return savedPatient;
    }

    //get
    public List<Patient> getPatient() {
        return patientRepo.findAll();
    }

    public Optional<Patient> getById(Integer id) {
        return patientRepo.findById(id);
    }

    @Transactional
    public void deleteById(int id) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();

            // Find the associated user by email
            Optional<User> optionalUser = userRepo.findByEmail(patient.getPatient_email());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                userRepo.delete(user);
            } else {
                System.err.println("User not found for email: " + patient.getPatient_email());
            }

            // Delete the patient
            patientRepo.deleteById(id);
        } else {
            System.err.println("Patient not found for ID: " + id);
        }
    }

    public Patient updatePatient(int id, Patient patientDetails) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setPatient_name(patientDetails.getPatient_name());
            patient.setPatient_email(patientDetails.getPatient_email());
            patient.setPhone_number(patientDetails.getPhone_number());
            patient.setPatient_course(patientDetails.getPatient_course());
            patient.setUsername(patientDetails.getUsername());
            patient.setPassword(patientDetails.getPassword());

            Patient updatedPatient = patientRepo.save(patient);

            Optional<User> optionalUser = userRepo.findByUsername(patient.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPassword(updatedPatient.getPassword());
                user.setEmail(updatedPatient.getPatient_email());
                user.setUsername(updatedPatient.getUsername());
                userRepo.save(user);
            }

            return updatedPatient;
        } else {
            return null;
        }
    }
}
