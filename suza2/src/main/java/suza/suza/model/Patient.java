package suza.suza.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String patient_name;
        private String patient_email;
        private String phone_number;
        private String patient_course;
        private String username;
        private String password;




}
