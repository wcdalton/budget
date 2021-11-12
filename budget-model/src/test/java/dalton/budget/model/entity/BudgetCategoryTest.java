package dalton.budget.model.entity;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

public class BudgetCategoryTest {
    @Test
    public void testEqualsAndHashCode1() {
        BudgetCategory object = new BudgetCategory();
        BudgetCategory other = new BudgetCategory();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode2() {
        BudgetCategory object = BudgetCategory.builder().id("1").build();
        BudgetCategory other = BudgetCategory.builder().id("1").build();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode3() {
        BudgetCategory object = BudgetCategory.builder().id(UUID.randomUUID().toString()).build();
        BudgetCategory other = new BudgetCategory();
        assert object.hashCode() != other.hashCode();
        assert !object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode4() {
        BudgetCategory object = BudgetCategory.builder().name("Utilities").build();
        BudgetCategory other = new BudgetCategory();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode5() {
        BudgetCategory object = BudgetCategory.builder().budgetId("1").build();
        BudgetCategory other = new BudgetCategory();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode6() {
        BudgetCategory object = BudgetCategory.builder().budget(new Budget()).build();
        BudgetCategory other = new BudgetCategory();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode7() {
        BudgetCategory object = new BudgetCategory();
        object.setTransactions(Collections.emptySet());
        BudgetCategory other = new BudgetCategory();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }
}
