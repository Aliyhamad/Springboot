package suza.suza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suza.suza.model.Doctor;
import suza.suza.model.Patient;
import suza.suza.service.DoctorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v8/doctor")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {

    @Autowired
    public DoctorService doctorService;

    //post
    @PostMapping
    public Doctor post (@RequestBody Doctor doctor){
        return doctorService.post(doctor);
    }

    @GetMapping
    public List<Doctor> getDoctor(){
        return doctorService.getDoctor();
    }

    @GetMapping("/{id}")
    public Optional<Doctor> getById(@PathVariable Integer id){
        return doctorService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        doctorService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int id, @RequestBody Doctor doctorDetails) {
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctorDetails);
        if (updatedDoctor != null) {
            return ResponseEntity.ok(updatedDoctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
