package dalton.budget.service.rest.api;

import dalton.budget.model.entity.Budget;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IBudgetService {
    @GetMapping(value = "/budget", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    Budget budget(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year);

    @PostMapping(value = "/budget",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    Budget budget(@RequestBody Budget budget);
}
