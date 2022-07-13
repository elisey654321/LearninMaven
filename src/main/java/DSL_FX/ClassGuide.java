package DSL_FX;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "guide")
public class ClassGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer code;

    private String name;
    private String nameDetail;
    private String typeClass;
}
