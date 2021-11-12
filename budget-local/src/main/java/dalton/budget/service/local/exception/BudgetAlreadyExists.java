package dalton.budget.service.local.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BudgetAlreadyExists extends RuntimeException {
    private static final long serialVersionUID = -7901456503577595776L;
    private static final String MESSAGE_FORMAT = "Budget already exists for %s";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/uuuu");

    public BudgetAlreadyExists(LocalDate localDate) {
        super(String.format(MESSAGE_FORMAT, DATE_FORMAT.format(localDate)));
    }
}
