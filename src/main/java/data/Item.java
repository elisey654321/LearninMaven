package data;

import DSL_FX.DataDSL;
import DSL_FX.Guide;
import lombok.*;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Item extends Guide {

    @DataDSL
    private String name;
    @DataDSL
    private String weight;
    @DataDSL
    private String height;
    @DataDSL
    private String width;

    @DataDSL
    private String globalName;
}
