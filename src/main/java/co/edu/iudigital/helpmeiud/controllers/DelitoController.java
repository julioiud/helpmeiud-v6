package co.edu.iudigital.helpmeiud.controllers;

import co.edu.iudigital.helpmeiud.models.Delito;
import co.edu.iudigital.helpmeiud.services.ifaces.IDelitoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delitos")
//@Api(value = "/delitos", tags = {"Delitos"})
public class DelitoController {

    // TODO: USAR DTOS
    @Autowired
    private IDelitoService delitoService;

    @Operation(
            summary = "Guardar un delito",
            description = "Endpoint para guardar un delito"
    )
    @ResponseStatus(HttpStatus.CREATED)
    // TODO: SWAGGER PARA OTRAS RESPUESTAS
    @PostMapping
    public ResponseEntity<Delito> save(@RequestBody Delito delito) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(delitoService.crearDelito(delito));
    }

    @Operation(
            summary = "Actualizar un delito",
            description = "Endpoint para actualizar un delito por ID"
    )
    @ResponseStatus(HttpStatus.CREATED)
    // TODO: SWAGGER PARA OTRAS RESPUESTAS
    @PutMapping("/{id}")
    public ResponseEntity<Delito> update(
            @RequestBody Delito delito,
            @PathVariable(value = "id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(delitoService.actualizarDelitoPorID(id, delito));
    }

    @Operation(
            summary = "Eliminar un delito",
            description = "Endpoint para eliminar un delito por ID"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // TODO: SWAGGER PARA OTRAS RESPUESTAS
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable(value = "id") Long id
    ) {
        delitoService.eliminarDelitoPorID(id);
    }


    @Operation(
            summary = "Consultar un delito",
            description = "Endpoint para consultar un delito por ID"
    )
    @ResponseStatus(HttpStatus.OK)
    // TODO: SWAGGER PARA OTRAS RESPUESTAS
    @GetMapping("/{id}")
    public ResponseEntity<Delito> getOne(
            @PathVariable(value = "id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(delitoService.consultarDelitoPorID(id));
    }

    @Operation(
            summary = "Consultar todos los delitos",
            description = "Endpoint para consultar todos los delitos"
    )
    @ResponseStatus(HttpStatus.OK)
    // TODO: SWAGGER PARA OTRAS RESPUESTAS
    @GetMapping
    public ResponseEntity<List<Delito>> index() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(delitoService.consultarDelitos());
    }
}
