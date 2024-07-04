package co.edu.iudigital.helpmeiud.repositories;

import co.edu.iudigital.helpmeiud.models.Caso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICasoRepository extends JpaRepository<Caso, Long> {

    List<Caso> findAllByLatitud(Float latitud); // Ejemplo para consultar por Latitud

    @Query("UPDATE Caso c SET c.visible= ?1 AND c.id = ?2")
    Boolean setVisible(Boolean visible, Long id);
}
