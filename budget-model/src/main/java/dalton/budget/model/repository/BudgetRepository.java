package dalton.budget.model.repository;

import dalton.budget.model.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BudgetRepository extends JpaRepository<Budget, String>, QuerydslPredicateExecutor<Budget> {
}
