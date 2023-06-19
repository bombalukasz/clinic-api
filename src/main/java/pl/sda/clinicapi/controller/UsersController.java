package pl.sda.clinicapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.sda.clinicapi.dto.UserDTO;
import pl.sda.clinicapi.service.UsersService;
import pl.sda.clinicapi.utils.PageReqUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Users Controller")
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(UsersController.USERS_ENDPOINT)
public class UsersController {

    public final static String USERS_ENDPOINT = "/api/users";

    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getPage(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "50") int size,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false) String sortColumn) {

        PageRequest pageReq = PageReqUtils.createPageReq(page, size, sortDirection, sortColumn);
        return ResponseEntity.ok(usersService.findAll(pageReq));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usersService.create(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateById(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(usersService.update(id, userDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usersService.deleteById(id);
    }

    @PatchMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.OK)
    public void disableById(@PathVariable Long id) {
        usersService.disable(id);
    }

    @PatchMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.OK)
    public void enableById(@PathVariable Long id) {
        usersService.enable(id);
    }
}
