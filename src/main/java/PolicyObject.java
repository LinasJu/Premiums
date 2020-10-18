import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PolicyObject {
    private String name;
    private List<SubObject> subObjects;
}
