import java.util.*;
import java.util.stream.Collectors;

public class PremiumCalculator {

    /**
     * @param policy gets whole policy with its objects and sub-objects
     * @return calculated premium to pay
     */
    public float calculate(Policy policy) {
        Set<RiskType> presentRiskTypes = getRiskTypes(policy);
        Map<RiskType, List<SubObject>> sortedSubObjects = getSortedSubObjects(policy.getObjects(), presentRiskTypes);

        Map<RiskType, Float> riskTypesWithSubObjectValueSums = getSubObjectValuesSum(sortedSubObjects);

        float sumOfRiskPremiums = 0f;
        for (Map.Entry<RiskType, Float> entry : riskTypesWithSubObjectValueSums.entrySet()) {
            sumOfRiskPremiums += countRiskTypePremium(entry.getKey(), entry.getValue());

        }
        return roundNumberToTwoDecimals(sumOfRiskPremiums);
    }

    /**
     * more efficient way to round float to scale of two numbers after comma than 'BigDecimal setScale', and 'Math.round / Math.pow'
     * @param number the sum to round to two decimals
     * @return rounded sum
     */
    public static float roundNumberToTwoDecimals(float number) {
        int pow = 10;
        for (int i = 1; i < 2; i++)
            pow *= 10;
        float tmp = number * pow;
        return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
    }

    private float countRiskTypePremium(RiskType key, Float sumInsuredForType) {
        return key.getRiskValue(sumInsuredForType) * sumInsuredForType;
    }

    private Map<RiskType, Float> getSubObjectValuesSum(Map<RiskType, List<SubObject>> sortedSubObjects) {
        Map<RiskType, Float> riskTypeAndValuesSum = new HashMap<>();

        for (Map.Entry<RiskType, List<SubObject>> entry : sortedSubObjects.entrySet()) {
            riskTypeAndValuesSum.put(entry.getKey(), entry.getValue().stream().map(SubObject::getSumInsured)
                    .reduce(0f, Float::sum));
        }
        return riskTypeAndValuesSum;
    }

    /**
     * @param policy whole policy
     * @return set of present risk types in policies sub-objects
     */
    private Set<RiskType> getRiskTypes(Policy policy) {
        return policy.getObjects().stream()
                .flatMap(policyObject -> policyObject.getSubObjects().stream())
                .flatMap(subObject -> subObject.getRiskTypes().stream()).collect(Collectors.toSet());
    }

    /**
     * @param objects   Policy objects to sort
     * @param riskTypes existing risk types in policy
     * @return sorted Map of Sub-objects by risk type
     */
    public Map<RiskType, List<SubObject>> getSortedSubObjects(List<PolicyObject> objects, Set<RiskType> riskTypes) {
        Map<RiskType, List<SubObject>> sortedSubObjects = new HashMap<>();

        for (RiskType type : riskTypes) {
            List<SubObject> subObjectsOfRiskType = objects.stream()
                    .flatMap(policyObject -> policyObject.getSubObjects().stream()
                            .filter(subObject -> subObject.getRiskTypes().contains(type))).collect(Collectors.toList());
            sortedSubObjects.put(type, subObjectsOfRiskType);
        }

        return sortedSubObjects;
    }
}
