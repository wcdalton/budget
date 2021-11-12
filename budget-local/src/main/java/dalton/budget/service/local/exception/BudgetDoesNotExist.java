package dalton.budget.service.local.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BudgetDoesNotExist extends RuntimeException {
    private static final long serialVersionUID = -5326423340784746730L;
    private static final String MESSAGE_FORMAT = "Budget does not exist for %s";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/uuuu");

    public BudgetDoesNotExist(LocalDate localDate) {
        super(String.format(MESSAGE_FORMAT, DATE_FORMAT.format(localDate)));
    }
}
