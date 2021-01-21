package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.handler.LoginSuccessHandler;
import web.service.UserDetailsServiceImpl;

//@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@ComponentScan(basePackages = {
//        "web.config",
//        "web.service",
//        "web.dao"})

//@ComponentScan("web")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired //  замена на конструктор ломает код - появляется циклическая ссылка
    private  UserDetailsServiceImpl userDetailsService;
    @Autowired
    private  LoginSuccessHandler successHandler;



//    @Autowired
//    public void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
////        auth.inMemoryAuthentication().withUser("a").password("{noop}vedanta@123#").roles("USER");
//    }

    @Autowired
    public void registerGlobalAuthentication2(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    /**
     *  Можно установить любое имя методу .
     *  Если имя не "configure" - то надо добавить @Autowired
     *  Если имя "configure" - то можно не добавлять @Autowired (если добавить - будет 4 вызова метода вместо 2х)
     *  Если имя "абракадабра с цифрами" то не длиннее 8 знаков.
     * "Возможнолюбоеимякотороетолькопонравится" - работает
     * a11111111a - не работает!
     * Не забыть сделать mvn CLEAN
     *
     *
     * @param http
     * @throws Exception
     */
//    @Override
//    @Autowired
//    protected void confasdfadfasdfasdfigure(AuthenticationManagerBuilder auth) throws Exception { // may by public too //configureInMemory..
//        System.out.println("inMemory");
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN", "USER");
//    }


    //@Override
    public void configure(HttpSecurity http) throws Exception { // protected
        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login").anonymous()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();
//

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

        //////////////////////////////////////////////////////
//        http
//             .authorizeRequests().antMatchers("/**").hasAnyRole("USER", "ADMIN").and().formLogin()
//                // делаем страницу регистрации недоступной для авторизированных пользователей
////                .authorizeRequests()
////                //страницы аутентификаци доступна всем
////                //.antMatchers("/").authenticated()
////                .antMatchers("/user" ).hasAnyRole("USER", "ADMIN")
////                .antMatchers("/admin/**").hasRole("ADMIN")
////                .antMatchers("/login").anonymous()
////                .anyRequest().authenticated()
////
////
////                .and()
////                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//                .permitAll()
//                .successHandler(successHandler);
//
//        http.logout()
//                // разрешаем делать логаут всем
//                .permitAll()
//                // указываем URL логаута
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                // указываем URL при удачном логауте
//                .logoutSuccessUrl("/login?logout")
//                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
//                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
