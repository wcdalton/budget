package dalton.budget.service.local.impl;

import com.querydsl.core.types.Predicate;
import dalton.budget.model.entity.Budget;
import dalton.budget.model.entity.QBudget;
import dalton.budget.model.repository.BudgetRepository;
import dalton.budget.service.local.api.IBudgetLocal;
import dalton.budget.service.local.exception.BudgetAlreadyExists;
import dalton.budget.service.local.exception.BudgetDoesNotExist;

import javax.annotation.Nullable;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Transactional
public class BudgetLocal implements IBudgetLocal {
    private final BudgetRepository budgetRepository;

    public BudgetLocal(BudgetRepository budgetRepository) {
        Objects.requireNonNull(budgetRepository);
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget create(@Nullable Budget budget) {
        LocalDate now = budget == null ? LocalDate.now() : LocalDate.of(budget.getYear(), budget.getMonth(), 1);
        Predicate predicate = QBudget.budget.month.eq(now.getMonthValue())
                .and(QBudget.budget.year.eq(now.getYear()));
        if (budgetRepository.exists(predicate)) {
            throw new BudgetAlreadyExists(now);
        }
        if (budget != null) {
            budget.setId(UUID.randomUUID().toString());
        }
        Budget toSave = budget != null ? budget : Budget.builder()
                .id(UUID.randomUUID().toString())
                .month(now.getMonthValue())
                .year(now.getYear())
                .build();
        return budgetRepository.saveAndFlush(toSave);
    }

    @Override
    public void update(Budget budget) {
        Objects.requireNonNull(budget);
        Budget existing = budget(budget.getId());
        if (existing == null) {
            existing = budget(budget.getYear(), budget.getMonth());
        }
        if (existing == null) {
            throw new BudgetDoesNotExist(LocalDate.of(budget.getYear(), budget.getMonth(), 1));
        }
        existing.setAmount(budget.getAmount());
        if (budget.getCategories() != null && !budget.getCategories().isEmpty()) {
            existing.setCategories(budget.getCategories());
        }
        budgetRepository.saveAndFlush(existing);
    }

    @Override
    public Budget budget() {
        LocalDate now = LocalDate.now();
        return budget(now.getYear(), now.getMonthValue());
    }

    @Override
    public Budget budget(String id) {
        Objects.requireNonNull(id);
        return budgetRepository.findById(id).orElse(null);
    }

    @Override
    public Budget budget(int year, int month) {
        Predicate predicate = QBudget.budget.month.eq(month)
                .and(QBudget.budget.year.eq(year));
        Iterable<Budget> budgets = budgetRepository.findAll(predicate);
        return budgets.iterator().hasNext() ? budgets.iterator().next() : null;
    }

    @Override
    public void delete(String id) {
        Objects.requireNonNull(id);
        budgetRepository.deleteAllById(List.of(id));
    }
}
