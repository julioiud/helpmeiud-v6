package co.edu.iudigital.helpmeiud.controllers;

import co.edu.iudigital.helpmeiud.dtos.casos.CasoRequestDTO;
import co.edu.iudigital.helpmeiud.dtos.casos.CasoRequestVisibleDTO;
import co.edu.iudigital.helpmeiud.dtos.casos.CasoResponseDTO;
import co.edu.iudigital.helpmeiud.exceptions.RestException;
import co.edu.iudigital.helpmeiud.services.ifaces.ICasoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Casos Controller", description = "Controlador para gestión de casos")
@RestController
@RequestMapping("/casos")
@Slf4j
public class CasoController {

    @Autowired
    private ICasoService casoService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )
    @Operation(
            summary = "Consultar todos los casos",
            description = "Endpoint para consultar todos los casos"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<CasoResponseDTO>> index() throws RestException {
        log.info("Ejecutando index de CasoController");
        return ResponseEntity
                .ok()
                .body(casoService.consultarCasos());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )
    @Operation(
            summary = "Consultar todos los casos visibles",
            description = "Endpoint para consultar todos los casos visibles"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/visibles")
    public ResponseEntity<List<CasoResponseDTO>> indexVisible() throws RestException {
        log.info("Ejecutando indexVisible de CasoController");
        return ResponseEntity
                .ok()
                .body(casoService.consultarCasosVisibles());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )
    @Operation(
            summary = "Crear un Caso",
            description = "Endpoint para crear un caso"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<CasoResponseDTO> create(
            @RequestBody CasoRequestDTO caso, Authentication authentication
    ) throws RestException { // TODO: IMPLEMENTAR Authorization para registrar caso con la persona que esté autenticada
        log.info("Ejecutando create de CasoController");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(casoService.guardarCaso(caso, authentication));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )
    @Operation(
            summary = "Visualizar u ocultar un Caso",
            description = "Endpoint para visualizar u ocultar un caso"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updateVisible(
            @PathVariable Long id,
            @RequestBody CasoRequestVisibleDTO request
    ) throws RestException {
        log.info("Ejecutando updateVisible de CasoController");
        final Boolean visible = request.getVisible();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(casoService.visibilizar(visible, id));
    }
}
