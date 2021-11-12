package dalton.budget.model.repository;

import dalton.budget.model.entity.CategoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CategoryTransactionRepository
        extends JpaRepository<CategoryTransaction, String>, QuerydslPredicateExecutor<CategoryTransaction> {
}
