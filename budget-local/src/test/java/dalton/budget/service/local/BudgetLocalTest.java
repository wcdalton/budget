package dalton.budget.service.local;


import com.querydsl.core.types.Predicate;
import dalton.budget.model.entity.Budget;
import dalton.budget.model.entity.QBudget;
import dalton.budget.model.repository.BudgetRepository;
import dalton.budget.service.local.exception.BudgetAlreadyExists;
import dalton.budget.service.local.exception.BudgetDoesNotExist;
import dalton.budget.service.local.impl.BudgetLocal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BudgetLocalTest {
    @Mock
    BudgetRepository mockRepository;

    @Captor
    private ArgumentCaptor<List<String>> deleteCaptor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConstructorWithNull() {
        assertThrows(NullPointerException.class, () -> new BudgetLocal(null));
    }

    @Test
    public void testConstructor() {
        new BudgetLocal(mockRepository);
    }

    @Test
    public void testCreateFails() {
        when(mockRepository.exists(any(Predicate.class))).thenReturn(true);
        BudgetLocal local = new BudgetLocal(mockRepository);
        BudgetAlreadyExists thrown = assertThrows(BudgetAlreadyExists.class, () -> local.create(null));
        LocalDate now = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("MM/uuuu").format(now);
        assertEquals(String.format("Budget already exists for %s", date), thrown.getMessage());
    }

    @Test
    public void testCreateSaves() {
        when(mockRepository.exists(any(Predicate.class))).thenReturn(false);
        BudgetLocal local = new BudgetLocal(mockRepository);
        local.create(null);
        verify(mockRepository, times(1)).saveAndFlush(any(Budget.class));
    }

    @Test
    public void testUpdateFails() {
        when(mockRepository.findById(any(String.class))).thenReturn(Optional.empty());
        when(mockRepository.findAll(any(Predicate.class))).thenReturn(Collections.emptyList());
        BudgetLocal local = new BudgetLocal(mockRepository);
        BudgetDoesNotExist thrown = assertThrows(BudgetDoesNotExist.class,
                () -> local.update(Budget.builder().id("someId").year(2021).month(11).build()));
        LocalDate now = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("MM/uuuu").format(now);
        assertEquals(String.format("Budget does not exist for %s", date), thrown.getMessage());
    }

    @Test
    public void testUpdateModifiesAmountAndSaves() {
        when(mockRepository.findById(any(String.class))).thenReturn(Optional.of(new Budget()));
        BudgetLocal local = new BudgetLocal(mockRepository);
        local.update(Budget.builder().id("someId").amount(7500d).build());
        ArgumentCaptor<Budget> argument = ArgumentCaptor.forClass(Budget.class);
        verify(mockRepository, times(1)).saveAndFlush(argument.capture());
        assertEquals(7500d, argument.getValue().getAmount());
    }

    @Test
    public void testCurrentBudget() {
        LocalDate now = LocalDate.now();
        Predicate predicate = QBudget.budget.month.eq(now.getMonthValue())
                .and(QBudget.budget.year.eq(now.getYear()));
        when(mockRepository.findAll(eq(predicate)))
                .thenReturn(List.of(Budget.builder().id("1").build(), Budget.builder().id("2").build()));
        BudgetLocal local = new BudgetLocal(mockRepository);
        Budget result = local.budget();
        assertNotNull(result);
        assertEquals("1", result.getId());
    }

    @Test
    public void testCurrentBudgetNotFound() {
        LocalDate now = LocalDate.now();
        Predicate predicate = QBudget.budget.month.eq(now.getMonthValue())
                .and(QBudget.budget.year.eq(now.getYear()));
        when(mockRepository.findAll(eq(predicate))).thenReturn(Collections.emptyList());
        BudgetLocal local = new BudgetLocal(mockRepository);
        assertNull(local.budget());
    }

    @Test
    public void testDeleteWithNull() {
        BudgetLocal local = new BudgetLocal(mockRepository);
        assertThrows(NullPointerException.class, () -> local.delete(null));
    }

    @Test
    public void testDelete() {
        BudgetLocal local = new BudgetLocal(mockRepository);
        local.delete("someId");
        verify(mockRepository, times(1)).deleteAllById(deleteCaptor.capture());
        assertEquals(1, deleteCaptor.getValue().size());
        assertTrue(deleteCaptor.getValue().contains("someId"));
    }
}
