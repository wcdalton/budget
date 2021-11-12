package dalton.budget.model.entity;

import com.google.gson.Gson;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "budget")
public class Budget implements Serializable {
    private static final long serialVersionUID = -4894184300256398378L;
    @Id
    private String id;
    private Integer month;
    private Integer year;
    private Double amount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
    private Set<BudgetCategory> categories;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Budget)) {
            return false;
        }
        Budget other = (Budget) obj;
        return Objects.equals(id, other.id);
    }

    public String json() {
        return new Gson().toJson(this);
    }
}
