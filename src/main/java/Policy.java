import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Policy {
    private String number;
    private Status status;
    private List<PolicyObject> objects;
}
