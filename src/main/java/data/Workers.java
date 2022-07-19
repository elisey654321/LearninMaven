package data;

import DSL_FX.DataDSL;
import DSL_FX.Guide;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Workers extends Guide {

    @DataDSL
    private String name;

    @DataDSL
    private String post;

    @DataDSL
    private String dateEmployment;

}
