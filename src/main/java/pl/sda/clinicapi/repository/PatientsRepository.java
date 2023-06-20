package pl.sda.clinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.clinicapi.model.Patient;

@Repository
public interface PatientsRepository extends JpaRepository<Patient, Long> {

}
