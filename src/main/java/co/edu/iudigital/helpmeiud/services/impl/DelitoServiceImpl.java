package co.edu.iudigital.helpmeiud.services.impl;

import co.edu.iudigital.helpmeiud.exceptions.ErrorDto;
import co.edu.iudigital.helpmeiud.exceptions.InternalServerErrorException;
import co.edu.iudigital.helpmeiud.exceptions.NotFoundException;
import co.edu.iudigital.helpmeiud.exceptions.RestException;
import co.edu.iudigital.helpmeiud.models.Delito;
import co.edu.iudigital.helpmeiud.repositories.IDelitoRepository;
import co.edu.iudigital.helpmeiud.services.ifaces.IDelitoService;
import co.edu.iudigital.helpmeiud.utils.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DelitoServiceImpl implements IDelitoService { // como lo voy a hacer

    @Autowired
    private IDelitoRepository delitoRepository;

    @Override
    public Delito crearDelito(Delito delito) throws RestException {
        log.info("crearDelito DelitoService");
        try{
            return delitoRepository.save(delito);
        }catch (Exception e) {
            throw new InternalServerErrorException(
                    ErrorDto.builder()
                            // TODO: COLOCAR COMO CONSTANTES LOS STRINGS 'QUEMADOS'
                            .error("Error General")
                            .status(500)
                            .message(e.getMessage())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

    }

    @Transactional
    @Override
    public Delito actualizarDelitoPorID(Long id, Delito delito) throws RestException {
        log.info("actualizarDelitoPorID DelitoService");
        Delito delitoBD = delitoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorDto.builder()
                                        .error(Messages.NO_ENCONTRADO)
                                        .message(Messages.DELIT_NO_EXISTE)
                                        .status(404)
                                        .date(LocalDateTime.now())
                                        .build())
                );
        delitoBD.setNombre(delito.getNombre());
        delitoBD.setDescripcion(delito.getDescripcion());
        try{
            return delitoRepository.save(delitoBD);
        }catch (Exception e) {
            log.error("Error actualizando delito casos: {} {}",e.getMessage(), e.getCause());
            throw new InternalServerErrorException(
                    ErrorDto.builder()
                            // TODO: COLOCAR COMO CONSTANTES LOS STRINGS 'QUEMADOS'
                            .error("Error General")
                            .status(500)
                            .message("ha ocurrido un error, consulta con el desarrollador")
                            .date(LocalDateTime.now())
                            .build()
            );
        }

    }

    @Transactional // ambigüo
    @Override
    public void eliminarDelitoPorID(Long id) throws RestException {
        log.info("eliminarDelitoPorID DelitoService");
        delitoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Delito consultarDelitoPorID(Long id) throws RestException {
        log.info("consultarDelitoPorID DelitoService");
        return delitoRepository.findById(id)
                .orElseThrow(() ->
                    new NotFoundException(
                        ErrorDto.builder()
                            .error(Messages.NO_ENCONTRADO)
                            .message(Messages.DELIT_NO_EXISTE)
                            .status(400)
                            .date(LocalDateTime.now())
                            .build())
                );
           /* Optional<Delito> delitoOptional = delitoRepository.findById(id);

            if(delitoOptional.isPresent()) {
                return delitoOptional.get();
            }else{
                throw new NotFoundException(
                        ErrorDto.builder()
                                .error("No encontrado")
                                .message("Delito No existe")
                                .status(400)
                                .date(LocalDateTime.now())
                                .build()
                );
            }*/
    }

    @Override
    public List<Delito> consultarDelitos() throws RestException{
        log.info("consultarDelitos DelitoService");
        try{
            return delitoRepository.findAll();
        }catch (Exception e) {
            throw new InternalServerErrorException(
                    ErrorDto.builder()
                            // TODO: COLOCAR COMO CONSTANTES LOS STRINGS 'QUEMADOS'
                            .error("Error General")
                            .status(500)
                            .message(e.getMessage())
                            .date(LocalDateTime.now())
                            .build()
            );
        }
    }
}
