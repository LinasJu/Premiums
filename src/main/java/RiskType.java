/**
 * @author Linas
 *
 */
public enum RiskType {
    FIRE {
        @Override
        public Float getRiskValue(Float sumInsured) {
            if (sumInsured > 100) {
                return 0.024f;
            }
            return 0.014f;
        }
    },
    THEFT {
        @Override
        public Float getRiskValue(Float sumInsured) {
            if (sumInsured >= 15) {
                return 0.05f;
            }
            return 0.11f;
        }
    };

    public abstract Float getRiskValue(Float sumInsured);
}

/*fire 0.014, 0.024*/
/*theft 0.11, 0.05*/
