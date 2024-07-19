package suza.suza.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suza.suza.model.Doctor;
import suza.suza.model.User;
import suza.suza.repository.DoctorRepo;
import suza.suza.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private UserRepo userRepo;

    //post
    public Doctor post(Doctor doctor) {
        Doctor savedDoctor = doctorRepo.save(doctor);

        User user = new User();
        user.setPassword(savedDoctor.getPassword());
        user.setEmail(savedDoctor.getDoctor_email());
        user.setUsername(savedDoctor.getUsername());
        user.setRole("doctor");
        userRepo.save(user);

        return savedDoctor;
    }

    //get
    public List<Doctor> getDoctor() {
        return doctorRepo.findAll();
    }

    public Optional<Doctor> getById(Integer id) {
        return doctorRepo.findById(id);
    }

    @Transactional
    public void deleteById(int id) {
        Optional<Doctor> optionalDoctor = doctorRepo.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            // Find the associated user by email
            Optional<User> optionalUser = userRepo.findByEmail(doctor.getDoctor_email());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                userRepo.delete(user);
            } else {
                System.err.println("User not found for email: " + doctor.getDoctor_email());
            }
            // Delete the doctor
            doctorRepo.deleteById(id);
        } else {
            System.err.println("Doctor not found for ID: " + id);
        }
    }

    public Doctor updateDoctor(int id, Doctor doctorDetails) {
        Optional<Doctor> optionalDoctor = doctorRepo.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            doctor.setDoctor_name(doctorDetails.getDoctor_name());
            doctor.setDoctor_address(doctorDetails.getDoctor_address());
            doctor.setDoctor_email(doctorDetails.getDoctor_email());
            doctor.setSpecialization(doctorDetails.getSpecialization());
            doctor.setPhone_no(doctorDetails.getPhone_no());
            doctor.setUsername(doctorDetails.getUsername());
            doctor.setPassword(doctorDetails.getPassword());

            Doctor updatedDoctor = doctorRepo.save(doctor);

            Optional<User> optionalUser = userRepo.findByUsername(doctor.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPassword(updatedDoctor.getPassword());
                user.setEmail(updatedDoctor.getDoctor_email());
                user.setUsername(updatedDoctor.getUsername());
                userRepo.save(user);
            }

            return updatedDoctor;
        } else {
            return null;
        }
    }
}
