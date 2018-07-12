package sa.common.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Component
public class WebSecurityConfiguration extends ResourceServerConfigurerAdapter {
    private static final String WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String READ_SCOPE = "#oauth2.hasScope('read')";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET).access(READ_SCOPE)
                .antMatchers(HttpMethod.GET).access(WRITE_SCOPE)
                .antMatchers(HttpMethod.POST).access(WRITE_SCOPE)
                .antMatchers(HttpMethod.PUT).access(WRITE_SCOPE)
                .antMatchers(HttpMethod.DELETE).access(WRITE_SCOPE);
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}