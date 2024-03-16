package com.mvc.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // 啟用Spring Security
@Configuration // 表示這是一個配置類
public class WebSecurityConfig {
	
	@Bean // 定義一個Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 禁用CSRF（跨站請求偽造）保護
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/image", "/logout.html", "/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll() // 允許對/login和靜態資源（如CSS、JS、圖片）的訪問，無需認證
                .requestMatchers("/manager").hasRole("ADMIN") // 只有ADMIN角色可以访问/manager
                .requestMatchers("/", "/index.html", "/hello").hasAnyRole("USER", "ADMIN") // USER和ADMIN角色可以访问/和/hello
                .anyRequest().authenticated()) // 要求所有其他請求都必須經過認證
            .formLogin(form -> form
               .permitAll()) // 允許所有用戶訪問表單登錄頁面
            .logout(logout -> logout
            	.logoutSuccessUrl("/logout.html") // 登出成功後跳轉到 /login
             )
            .cors(Customizer.withDefaults()); // 啟用CORS（跨域資源共享），使用預設配置

        return http.build(); // 建立安全過濾器鏈
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.builder()
            .username("john")
            .password(encoder.encode("1234"))
            .roles("USER") // 將自動添加前娺字 "ROLE_"
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(encoder.encode("1234"))
            .roles("ADMIN") // 將自動添加前娺字 "ROLE_"
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
    
}

