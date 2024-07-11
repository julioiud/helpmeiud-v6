package co.edu.iudigital.helpmeiud.services.impl;

import co.edu.iudigital.helpmeiud.dtos.usuarios.UsuarioRequestDTO;
import co.edu.iudigital.helpmeiud.dtos.usuarios.UsuarioResponseDTO;
import co.edu.iudigital.helpmeiud.exceptions.RestException;
import co.edu.iudigital.helpmeiud.models.Role;
import co.edu.iudigital.helpmeiud.models.Usuario;
import co.edu.iudigital.helpmeiud.repositories.IRoleRepository;
import co.edu.iudigital.helpmeiud.repositories.IUsuarioRepository;
import co.edu.iudigital.helpmeiud.services.ifaces.IUsuarioService;
import co.edu.iudigital.helpmeiud.utils.UsuarioMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioResponseDTO> consultarTodos() throws RestException {
        return null;
    }

    @Override
    public UsuarioResponseDTO registrar(UsuarioRequestDTO usuarioRequestDTO)  throws RestException{
        // TODO: COLOCAR EL TRY-CATCH
        Usuario usuario = usuarioMapper.toUsuario(usuarioRequestDTO);

        usuario.setRoles(Collections.singletonList(new Role(2L)));
        usuario.setEnabled(true);

        usuario = usuarioRepository.save(usuario);


        return usuarioMapper.usuarioResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO consultarPorID(Long id)  throws RestException{
        return null;
    }

    @Override
    public Usuario findByUsername(String username) {
        return null;
    }

    @Override
    public UsuarioResponseDTO consultarPorUsername(String username)  throws RestException {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
