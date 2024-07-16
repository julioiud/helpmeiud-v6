package co.edu.iudigital.helpmeiud.dtos.usuarios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class UsuarioRequestUpdateDTO  implements Serializable {

    static final long serialVersionUID = 1L;

    String nombre;

    String apellido;

    String password;

    @JsonProperty("fecha_nacimiento")
    LocalDate fechaNacimiento;

    @JsonIgnore
    String image;
}
