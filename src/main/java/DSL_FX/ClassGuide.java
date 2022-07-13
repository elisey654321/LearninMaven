package DSL_FX;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "guide")
public class ClassGuide {
    @Id
    private String name;
    private String nameDetail;
    private String typeClass;
}
