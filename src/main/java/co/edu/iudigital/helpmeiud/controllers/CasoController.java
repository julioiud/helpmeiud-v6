package co.edu.iudigital.helpmeiud.controllers;

import co.edu.iudigital.helpmeiud.dtos.casos.CasoRequestDTO;
import co.edu.iudigital.helpmeiud.dtos.casos.CasoResponseDTO;
import co.edu.iudigital.helpmeiud.exceptions.RestException;
import co.edu.iudigital.helpmeiud.services.ifaces.ICasoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                .status(HttpStatus.OK)
                .body(casoService.consultarCasos());
    }

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
            @RequestBody CasoRequestDTO caso
    ) throws RestException {
        log.info("Ejecutando create de CasoController");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(casoService.guardarCaso(caso));
    }
}
