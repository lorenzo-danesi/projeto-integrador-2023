package br.ufsm.csi.springpi2023.security;

import br.ufsm.csi.springpi2023.dao.FuncionarioDao;
import br.ufsm.csi.springpi2023.model.Funcionario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: "+username);

        Funcionario usuario = new FuncionarioDao().getLogin(username);

        if(usuario == null){
            throw  new UsernameNotFoundException("Usuário ou senha inválidos");
        }else{
            UserDetails user = User.withUsername(usuario.getEmail())
                    .password(usuario.getSenha())
                    .authorities(usuario.getPermissao()).build();
            return user;
        }
    }
}
