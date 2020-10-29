import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author
 * Linas Jurgelėnas
 *
 */
public class Main {
    public static void main(String[] args) {

        //here you create policy objecty with its subObjects
        Policy policy = new Policy();
        PolicyObject policyObject = new PolicyObject();
        List<SubObject> subObjects = new ArrayList<>();

        SubObject subObject = new SubObject();
        subObject.setRiskTypes(Collections.singletonList(RiskType.FIRE));// could be set to have multiple riskTypes
        subObject.setSumInsured(100f);

        subObjects.add(subObject);

        subObject = new SubObject();
        subObject.setRiskTypes(Collections.singletonList(RiskType.THEFT));
        subObject.setSumInsured(8f);
        subObjects.add(subObject);

        policyObject.setSubObjects(subObjects);
        policy.setObjects(Collections.singletonList(policyObject));

        PremiumCalculator calculator = new PremiumCalculator();
        System.out.print("Premium to pay: " + calculator.calculate(policy));
    }
}
