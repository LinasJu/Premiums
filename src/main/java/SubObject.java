import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubObject {
    private String name;
    private Float sumInsured;
    private List<RiskType> riskTypes;
}
