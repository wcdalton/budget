package dalton.budget.service.rest.impl;

import dalton.budget.model.entity.Budget;
import dalton.budget.service.local.api.IBudgetLocal;
import dalton.budget.service.rest.api.IBudgetService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
public class BudgetService implements IBudgetService {
    private static final Logger log = LoggerFactory.getLogger(BudgetService.class);
    private final IBudgetLocal budgetLocal;

    public BudgetService(IBudgetLocal budgetLocal) {
        this.budgetLocal = budgetLocal;
    }

    @Override
    public Budget budget(String id, String month, String year) {
        if (StringUtils.isNotBlank(id)) {
            return budgetLocal.budget(id);
        } else if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
            int yearInt;
            try {
                yearInt = Integer.parseInt(year);
            } catch (NumberFormatException e) {
                log.error(String.format("Invalid query parameter value for year %s", year), e);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            int monthInt;
            try {
                monthInt = Integer.parseInt(month);
            } catch (NumberFormatException e) {
                log.error(String.format("Invalid query parameter value for month %s", month), e);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            return budgetLocal.budget(yearInt, monthInt);
        } else if (StringUtils.isNotBlank(year)) {
            int yearInt;
            try {
                yearInt = Integer.parseInt(year);
            } catch (NumberFormatException e) {
                log.error(String.format("Invalid query parameter value for year %s", year), e);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            int monthInt = LocalDate.now().getMonthValue();
            return budgetLocal.budget(yearInt, monthInt);
        } else if (StringUtils.isNotBlank(month)) {
            int monthInt;
            try {
                monthInt = Integer.parseInt(month);
            } catch (NumberFormatException e) {
                log.error(String.format("Invalid query parameter value for month %s", month), e);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            int yearInt = LocalDate.now().getYear();
            return budgetLocal.budget(yearInt, monthInt);
        } else {
            return budgetLocal.budget();
        }
    }

    @Override
    public Budget budget(Budget budget) {
        return budgetLocal.create(budget);
    }
}
