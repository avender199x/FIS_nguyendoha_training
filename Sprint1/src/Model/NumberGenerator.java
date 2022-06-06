package Model;

import java.util.Random;

public class NumberGenerator {
    private Random RAND;
    private String UPPER;
    private String DIGITS;

    public NumberGenerator(Random RAND, String UPPER, String DIGITS) {
        this.RAND = RAND;
        this.UPPER = UPPER;
        this.DIGITS = DIGITS;
    }

    public Random getRAND() {
        return RAND;
    }

    public void setRAND(Random RAND) {
        this.RAND = RAND;
    }

    public String getUPPER() {
        return UPPER;
    }

    public void setUPPER(String UPPER) {
        this.UPPER = UPPER;
    }

    public String getDIGITS() {
        return DIGITS;
    }

    public void setDIGITS(String DIGITS) {
        this.DIGITS = DIGITS;
    }
}
