package com.yog.test.springjpa.common;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //get user from security context holder once spring security is configured.
        return Optional.of("admin");
    }
}
