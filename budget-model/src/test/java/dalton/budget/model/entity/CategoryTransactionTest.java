package dalton.budget.model.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class CategoryTransactionTest {
    @Test
    public void testEqualsAndHashCode1() {
        CategoryTransaction object = new CategoryTransaction();
        CategoryTransaction other = new CategoryTransaction();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode2() {
        CategoryTransaction object = CategoryTransaction.builder().id("1").build();
        CategoryTransaction other = CategoryTransaction.builder().id("1").build();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode3() {
        CategoryTransaction object = CategoryTransaction.builder().id(UUID.randomUUID().toString()).build();
        CategoryTransaction other = new CategoryTransaction();
        assert object.hashCode() != other.hashCode();
        assert !object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode4() {
        CategoryTransaction object = CategoryTransaction.builder().name("Company B").build();
        CategoryTransaction other = new CategoryTransaction();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode5() {
        CategoryTransaction object = CategoryTransaction.builder().notes("Bugle Purchase").build();
        CategoryTransaction other = new CategoryTransaction();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode6() {
        CategoryTransaction object = CategoryTransaction.builder().amount(129.73).build();
        CategoryTransaction other = new CategoryTransaction();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode7() {
        CategoryTransaction object = CategoryTransaction.builder().expense(true).build();
        CategoryTransaction other = new CategoryTransaction();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode8() {
        CategoryTransaction object = CategoryTransaction.builder().categoryId("1").build();
        CategoryTransaction other = new CategoryTransaction();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode9() {
        CategoryTransaction object = CategoryTransaction.builder().category(new BudgetCategory()).build();
        CategoryTransaction other = new CategoryTransaction();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode10() {
        CategoryTransaction object = CategoryTransaction.builder().transactionDate(LocalDate.now()).build();
        CategoryTransaction other = new CategoryTransaction();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }
}
