package dalton.budget.model.entity;

import com.google.gson.Gson;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "category_transaction")
public class CategoryTransaction implements Serializable {
    private static final long serialVersionUID = 4637338730954542441L;
    @Id
    private String id;
    private String name;
    private String notes;
    private Double amount;
    private boolean expense;
    private LocalDate transactionDate;

    @Column(name = "category_id")
    private String categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BudgetCategory category;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CategoryTransaction)) {
            return false;
        }
        CategoryTransaction other = (CategoryTransaction) obj;
        return Objects.equals(id, other.id);
    }

    public String json() {
        return new Gson().toJson(this);
    }
}
