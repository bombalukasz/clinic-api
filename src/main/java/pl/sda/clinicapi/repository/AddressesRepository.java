package pl.sda.clinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.clinicapi.model.Address;

@Repository
public interface AddressesRepository extends JpaRepository<Address, Long> {
}
