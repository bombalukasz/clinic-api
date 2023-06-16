package pl.sda.clinicapi.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.sda.clinicapi.dto.PatientDTO;
import pl.sda.clinicapi.model.Patient;

@Mapper
public interface PatientsMapper {

    PatientsMapper INSTANCE = Mappers.getMapper(PatientsMapper.class);

    Patient map(PatientDTO patientDTO);

    PatientDTO map(Patient patient);
}
