package dalton.budget.service.local;

import dalton.budget.model.BudgetModelConfig;
import dalton.budget.model.repository.BudgetRepository;
import dalton.budget.service.local.api.IBudgetLocal;
import dalton.budget.service.local.impl.BudgetLocal;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(BudgetModelConfig.class)
public class BudgetLocalConfig {
    @Bean
    IBudgetLocal budgetLocal(BudgetRepository budgetRepository) {
        return new BudgetLocal(budgetRepository);
    }
}
