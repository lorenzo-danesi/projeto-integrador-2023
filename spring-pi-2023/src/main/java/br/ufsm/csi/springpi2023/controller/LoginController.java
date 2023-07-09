package br.ufsm.csi.springpi2023.controller;

import br.ufsm.csi.springpi2023.dao.FuncionarioDao;
import br.ufsm.csi.springpi2023.model.Funcionario;
import br.ufsm.csi.springpi2023.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<Object> autenticacao(@RequestBody Funcionario funcionario){

        System.out.println("Endereço de e-mail: "+funcionario.getEmail());
        System.out.println("Senha original: "+funcionario.getSenha());
        try{
                final Authentication authentication = this.authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(funcionario.getEmail(), funcionario.getSenha()));

                if(authentication.isAuthenticated()){
                    //colocamos nossa instancia autenticada no contexto do spring security
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    //funcionario.setPermissao(authentication.getAuthorities().toString().replace("[", "").replace("]", ""));
                    Funcionario user = new FuncionarioDao().getFuncionario(funcionario.getEmail());
                    System.out.println("Gerando token de autorização ****");
                    String token = new JWTUtil().geraToken(funcionario);

                    user.setToken(token);
                    funcionario.setSenha("");

                    return new ResponseEntity<>(user, HttpStatus.OK);
                }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
    }
}
