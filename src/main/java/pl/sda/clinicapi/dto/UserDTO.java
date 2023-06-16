package pl.sda.clinicapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.clinicapi.enums.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username must not be blank!")
    private String username;

    @NotBlank(message = "Password must not be blank!")
    private String password;

    @NotNull(message = "Role cannot be null!")
    private Role role;

    private boolean enabled;

    @NotBlank(message = "Email must not be blank!")
    private String email;
}
