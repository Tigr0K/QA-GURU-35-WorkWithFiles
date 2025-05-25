package model;

public class UpdateData {
    private int checkKey;
    private String checkCode;
    private int cclAttrKey;
    private int cclAttrResultKey;
    private int value;
    private int result;

    public int getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(int checkKey) {
        this.checkKey = checkKey;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public void setCclAttrKey(int cclAttrKey) {
        this.cclAttrKey = cclAttrKey;
    }

    public void setCclAttrResultKey(int cclAttrResultKey) {
        this.cclAttrResultKey = cclAttrResultKey;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public int getCclAttrKey() {
        return cclAttrKey;
    }

    public int getCclAttrResultKey() {
        return cclAttrResultKey;
    }

    public int getValue() {
        return value;
    }

    public int getResult() {
        return result;
    }
}
