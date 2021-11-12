package dalton.budget.service.local.api;

import dalton.budget.model.entity.Budget;

public interface IBudgetLocal {
    Budget create(Budget budget);
    void update(Budget budget);
    Budget budget();
    Budget budget(String id);
    Budget budget(int year, int month);
    void delete(String id);
}
