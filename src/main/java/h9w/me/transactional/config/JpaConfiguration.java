package h9w.me.transactional.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "h9w.me.transactional")
@EnableJpaRepositories(basePackages = "h9w.me.transactional")
public class JpaConfiguration {
}
