package data;

import DSL_FX.Detail;
import DSL_FX.Guide;
import lombok.*;

import java.util.ArrayList;

@Data
public class Company extends Guide {
    public Company() {

        //++Add columns
        ArrayList<Detail> details = getDetails();
        details.add(new Detail("companyFullName", String.class.getTypeName()));
        setDetails(details);
        //--Add columns

        initialize();
    }
}
