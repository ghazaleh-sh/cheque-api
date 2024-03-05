package ir.co.sadad.cheque.config;

import ir.bmi.identity.client.security.config.BmiOAuth2Properties;
import ir.bmi.identity.client.security.config.BmiSsoTokenProvider;
import ir.bmi.identity.client.security.exception.BmiSsoMvcExceptionHandler;
import ir.bmi.identity.client.security.exception.DefaultBmiSsoExceptionHandler;
import ir.bmi.identity.client.security.filter.SsoAuthenticationWebFilter;
import ir.bmi.identity.client.security.filter.SsoExcludedUrlsWebFilter;
import ir.co.sadad.cheque.security.AuthoritiesConstants;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;
import tech.jhipster.config.JHipsterProperties;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JHipsterProperties jHipsterProperties;
    private final BmiSsoTokenProvider tokenProvider;
    private final BmiOAuth2Properties bmiOAuth2Properties;
    private final BmiSsoMvcExceptionHandler bmiSsoMvcExceptionHandler;
    private final SecurityProblemSupport problemSupport;

    public SecurityConfiguration(
        BmiSsoTokenProvider tokenProvider,
        JHipsterProperties jHipsterProperties,
        BmiOAuth2Properties bmiOAuth2Properties,
        SecurityProblemSupport problemSupport
    ) {
        this.tokenProvider = tokenProvider;
        this.bmiOAuth2Properties = bmiOAuth2Properties;
        this.bmiSsoMvcExceptionHandler = new DefaultBmiSsoExceptionHandler();
        this.problemSupport = problemSupport;
        this.jHipsterProperties = jHipsterProperties;
    }

    @Bean
    public StrictHttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowedHeaderNames(header -> true);
        firewall.setAllowedHeaderValues(header -> true);
        firewall.setAllowedParameterNames(parameter -> true);
        return firewall;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
            .disable()
            .addFilterBefore(this.getSsoExcludedUrlsWebFilter(), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
                httpServletResponse.setStatus(401);
                String errorMessage = e.getMessage();
                httpServletResponse.getOutputStream().write(errorMessage.getBytes());
            })
            .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
                httpServletResponse.setStatus(403);
                String errorMessage = e.getMessage();
                httpServletResponse.getOutputStream().write(errorMessage.getBytes());
            })
            .and()
            .headers()
            .contentSecurityPolicy(jHipsterProperties.getSecurity().getContentSecurityPolicy())
            .and()
            .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
            .and()
            .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; fullscreen 'self'; payment 'none'")
            .and()
            .frameOptions()
            .deny()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/swagger-ui.html/**").permitAll()
            .antMatchers("/v3/api-docs/**").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/api/admin/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/api/**").authenticated()
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/health/**").permitAll()
            .antMatchers("/management/info").permitAll()
            .antMatchers("/management/prometheus").permitAll()
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .anyRequest().authenticated()
            .and();
        //        .apply(securityConfigurerAdapter());
        // @formatter:on
    }

    private SsoExcludedUrlsWebFilter getSsoExcludedUrlsWebFilter() {
        return SsoExcludedUrlsWebFilter
            .builder()
            .excludeMatcher(this.getExcludedRequestMatcher())
            .filter(new SsoAuthenticationWebFilter(tokenProvider, bmiSsoMvcExceptionHandler))
            .build();
    }

    private RequestMatcher getExcludedRequestMatcher() {
        LinkedList<RequestMatcher> orRequestMatcherList = new LinkedList<>();
        for (String url : this.bmiOAuth2Properties.getExcludedUrls()) {
            if (!url.isEmpty()) {
                String[] split = url.split("\\:");
                final String pattern = split[0];
                final String method = split.length == 1 ? "*" : split[1];
                AntPathRequestMatcher matcher;
                if ("".equals(method) || "*".equals(method)) {
                    matcher = new AntPathRequestMatcher(pattern);
                } else {
                    matcher = new AntPathRequestMatcher(pattern, method);
                }
                orRequestMatcherList.add(matcher);
            }
        }

        return orRequestMatcherList.isEmpty() ? new StaticMatcher(false) : new OrRequestMatcher(orRequestMatcherList);
    }

    private static class StaticMatcher implements RequestMatcher {

        private final boolean matchTo;

        public StaticMatcher(boolean matchTo) {
            this.matchTo = matchTo;
        }

        @Override
        public boolean matches(HttpServletRequest request) {
            return matchTo;
        }
    }
}
