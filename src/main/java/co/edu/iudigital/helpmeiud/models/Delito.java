package co.edu.iudigital.helpmeiud.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "delitos")
public class Delito implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true, length = 100)
    String nombre;

    @Column
    String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    Usuario usuario;
}
