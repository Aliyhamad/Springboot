package suza.suza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suza.suza.model.Patient;
import suza.suza.service.PatientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v9/patient")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {
        @Autowired
        public PatientService patientService;

        //post
        @PostMapping
        public Patient post (@RequestBody Patient patient){
            return patientService.post(patient);
        }

        @GetMapping
        public List<Patient> getPatient(){
            return patientService.getPatient();
        }

        @GetMapping("/{id}")
        public Optional<Patient> getById(@PathVariable Integer id){
                return patientService.getById(id);
        }

        @DeleteMapping("/{id}")
        public void deleteById(@PathVariable Integer id){
                patientService.deleteById(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient patientDetails) {
                Patient updatedPatient = patientService.updatePatient(id, patientDetails);
                if (updatedPatient != null) {
                        return ResponseEntity.ok(updatedPatient);
                } else {
                        return ResponseEntity.notFound().build();
                }
        }
}

