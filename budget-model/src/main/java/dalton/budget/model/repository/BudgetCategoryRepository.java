package dalton.budget.model.repository;

import dalton.budget.model.entity.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BudgetCategoryRepository
        extends JpaRepository<BudgetCategory, String>, QuerydslPredicateExecutor<BudgetCategory> {
}
