package DSL_FX;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Guide {

    private String nameGuide;
    private ArrayList<Detail> details;

    public Guide() {
        details.add(new Detail("Name",Class<String>));


    }
}
