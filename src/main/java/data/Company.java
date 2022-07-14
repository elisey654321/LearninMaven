package data;

import DSL_FX.DataDSL;
import DSL_FX.Detail;
import DSL_FX.Guide;
import lombok.*;

import java.util.ArrayList;

@Data
public class Company extends Guide {

    @DataDSL
    private String companyFullName;
    @DataDSL
    private String inn;

    public Company() {

    }
}
