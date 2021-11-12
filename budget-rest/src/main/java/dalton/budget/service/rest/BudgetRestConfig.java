package dalton.budget.service.rest;

import dalton.budget.service.local.BudgetLocalConfig;
import dalton.budget.service.rest.impl.BudgetService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@AutoConfigureAfter(BudgetLocalConfig.class)
@Import(value = {BudgetService.class})
public class BudgetRestConfig {

}
