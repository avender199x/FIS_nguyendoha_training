package fis.java.bigexample.model.enums;

public enum Rank {
    TRANEE,JUNIOR,SENIOR,INSPECTOR,CHEF_INSPECTOR;

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
