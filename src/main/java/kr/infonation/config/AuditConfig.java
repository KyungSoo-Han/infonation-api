package kr.infonation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Configuration
public class AuditConfig implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Long userId= Long.valueOf(request.getHeader("userId"));

        return Optional.of(userId);
    }
}
