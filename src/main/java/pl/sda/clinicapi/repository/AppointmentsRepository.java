package pl.sda.clinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.clinicapi.model.Appointment;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
}
