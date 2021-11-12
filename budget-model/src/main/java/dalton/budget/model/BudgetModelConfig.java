package dalton.budget.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "dalton.budget.model.repository")
@EntityScan(basePackages = "dalton.budget.model.entity")
public class BudgetModelConfig {
}
