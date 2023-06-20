package pl.sda.clinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.clinicapi.model.Doctor;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctor, Long> {

}
