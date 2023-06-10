package br.ufsm.csi.springpi2023.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAutenticacao(AuthenticationManagerBuilder auth) throws Exception{
        System.out.println("... configurando o AuthenticationManager ****");
        auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public FiltroAutenticacao filtroAutenticacao() throws Exception{
        return new FiltroAutenticacao();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
        //        http
        .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
              //  .authenticationProvider(this.authProvider())
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/departamentos/listar").hasAuthority("Admin")
                .antMatchers(HttpMethod.POST,"/departamentos/adicionar").hasAuthority("Admin")
                .antMatchers(HttpMethod.PUT, "/departamentos/{id_departamento}").hasAuthority("Admin")
                .antMatchers(HttpMethod.DELETE, "/departamentos/{id_departamento}").hasAuthority("Admin")
                .antMatchers(HttpMethod.GET, "/cargos/listar").hasAuthority("Admin")
                .antMatchers(HttpMethod.POST, "/cargos/adicionar").hasAuthority("Admin")
                .antMatchers(HttpMethod.PUT, "/cargos/{id_produto}").hasAuthority("Admin")
                .antMatchers(HttpMethod.DELETE, "/cargos/{id_produto}").hasAuthority("Admin")
                .antMatchers(HttpMethod.GET, "/funcionarios/listar").hasAuthority("Admin")
                .antMatchers(HttpMethod.POST, "/funcionarios/adicionar").hasAuthority("Admin")
                .antMatchers(HttpMethod.PUT, "/funcionarios/{id_funcionario}").hasAuthority("Admin")
                .antMatchers(HttpMethod.DELETE, "/funcionarios/{id_funcionario}").hasAuthority("Admin");
               // .antMatchers(HttpMethod.GET, "/ponto/listar").hasAuthority("Funcionario");
               // .and().formLogin();

            http.addFilterBefore(this.filtroAutenticacao(), UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    public CorsFilter corsFilter() {
        System.out.println("configurando cors ....");
        final var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
