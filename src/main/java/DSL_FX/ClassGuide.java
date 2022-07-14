package DSL_FX;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "guide")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    private String name;
    private String nameDetail;
    private String typeClass;
}
