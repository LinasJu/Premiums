import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tests {

    @Test
    public void firstAcceptanceCriteria() {
        Policy policy = new Policy();
        PolicyObject policyObject = new PolicyObject();
        List<SubObject> subObjects = new ArrayList<>();

        SubObject subObject = new SubObject();
        subObject.setRiskTypes(Collections.singletonList(RiskType.FIRE));
        subObject.setSumInsured(100f);

        subObjects.add(subObject);

        subObject = new SubObject();
        subObject.setRiskTypes(Collections.singletonList(RiskType.THEFT));
        subObject.setSumInsured(8f);
        subObjects.add(subObject);

        policyObject.setSubObjects(subObjects);
        policy.setObjects(Collections.singletonList(policyObject));

        PremiumCalculator calculator = new PremiumCalculator();
        Assert.assertEquals(2.28, calculator.calculate(policy), 0.001f);
    }
    @Test
    public void secondAcceptanceCriteria() {
        Policy policy = new Policy();
        PolicyObject policyObject = new PolicyObject();
        List<SubObject> subObjects = new ArrayList<>();

        SubObject subObject = new SubObject();
        subObject.setRiskTypes(Collections.singletonList(RiskType.FIRE));
        subObject.setSumInsured(500f);

        subObjects.add(subObject);

        subObject = new SubObject();
        subObject.setRiskTypes(Collections.singletonList(RiskType.THEFT));
        subObject.setSumInsured(102.51f);
        subObjects.add(subObject);

        policyObject.setSubObjects(subObjects);
        policy.setObjects(Collections.singletonList(policyObject));

        PremiumCalculator calculator = new PremiumCalculator();
        Assert.assertEquals(17.13, calculator.calculate(policy), 0.001f);
    }
}
