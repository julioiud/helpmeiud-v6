package co.edu.iudigital.helpmeiud.services.impl;

import co.edu.iudigital.helpmeiud.models.Delito;
import co.edu.iudigital.helpmeiud.repositories.IDelitoRepository;
import co.edu.iudigital.helpmeiud.services.ifaces.IDelitoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DelitoServiceImpl implements IDelitoService { // como lo voy a hacer

    @Autowired
    private IDelitoRepository delitoRepository;

    @Override
    public Delito crearDelito(Delito delito) {
        log.info("crearDelito DelitoService");
        return delitoRepository.save(delito);
    }

    @Transactional
    @Override
    public Delito actualizarDelitoPorID(Long id, Delito delito) {
        log.info("actualizarDelitoPorID DelitoService");
        Delito delitoBD = delitoRepository.findById(id).orElseThrow(RuntimeException::new);
        delitoBD.setNombre(delito.getNombre());
        delitoBD.setDescripcion(delito.getDescripcion());
        return delitoRepository.save(delitoBD);
    }

    @Transactional // ambigüo
    @Override
    public void eliminarDelitoPorID(Long id) {
        log.info("eliminarDelitoPorID DelitoService");
        delitoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Delito consultarDelitoPorID(Long id) {
        log.info("consultarDelitoPorID DelitoService");
        return delitoRepository.findById(id).orElseThrow(RuntimeException::new);// TODO: EXCEPCIONES PERSONALIZADAS
    }

    @Override
    public List<Delito> consultarDelitos() {
        log.info("consultarDelitos DelitoService");
        return delitoRepository.findAll();
    }
}
