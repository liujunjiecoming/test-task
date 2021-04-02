package com.bocang.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.Random;

/**
 * @author Ljj
 * @version 1.0
 * @Description
 * @date 上午9:31 20-10-21
 */
@Configuration
public class AuditorConfig implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(new Random().nextLong());
    }
}
