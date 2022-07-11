package data;

import DSL_FX.Detail;
import DSL_FX.Guide;
import lombok.*;

import java.util.ArrayList;

@Data
public class Company extends Guide {
    public Company() {
        String nameClass = getClass().getName();
        setNameGuide(nameClass);

        ArrayList<Detail> details = getDetails();
        details.add(new Detail("companyFullName", String.class.getTypeName()));
        setDetails(details);
    }

    @Override
    public String toString() {
        return "Company{}";
    }
}
