package dalton.budget.model.entity;


import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;


public class BudgetTest {
    @Test
    public void testEqualsAndHashCode1() {
        Budget object = new Budget();
        Budget other = new Budget();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode2() {
        Budget object = Budget.builder().id("1").build();
        Budget other = Budget.builder().id("1").build();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode3() {
        Budget object = Budget.builder().id(UUID.randomUUID().toString()).build();
        Budget other = new Budget();
        assert object.hashCode() != other.hashCode();
        assert !object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode4() {
        Budget object = Budget.builder().month(1).build();
        Budget other = new Budget();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode5() {
        Budget object = Budget.builder().year(1).build();
        Budget other = new Budget();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }

    @Test
    public void testEqualsAndHashCode6() {
        Budget object = new Budget();
        object.setCategories(Collections.emptySet());
        Budget other = new Budget();
        assert object.hashCode() == other.hashCode();
        assert object.equals(other);
    }
}
