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
@Table(name = "budget_category")
public class BudgetCategory implements Serializable {
    private static final long serialVersionUID = 1826100688303649842L;
    @Id
    private String id;
    private String name;
    private Double amount;
    @Column(name = "budget_id", nullable = false)
    private String budgetId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Budget budget;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<CategoryTransaction> transactions;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BudgetCategory)) {
            return false;
        }
        BudgetCategory other = (BudgetCategory) obj;
        return Objects.equals(id, other.id);
    }

    public String json() {
        return new Gson().toJson(this);
    }
}
