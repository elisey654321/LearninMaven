package DSL_FX;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Guide {

    private String nameGuide;
    private ArrayList<Detail> details = new ArrayList<>();

    public Guide() {
        Detail name = new Detail("name", String.class.getName());
        Detail id = new Detail("id", String.class.getName());

        this.details.add(name);
        this.details.add(id);
    }
}
