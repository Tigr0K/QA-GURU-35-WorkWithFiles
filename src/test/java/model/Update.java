package model;

import java.util.List;

public class Update {
    private int fpCheckEntityKey;
    private List<UpdateData> updateData;

    public int getFpCheckEntityKey() {
        return fpCheckEntityKey;
    }

    public List<UpdateData> getUpdateData() {
        return updateData;
    }

    public void setFpCheckEntityKey(int fpCheckEntityKey) {
        this.fpCheckEntityKey = fpCheckEntityKey;
    }

    public void setUpdateD(List<UpdateData> updateData) {
        this.updateData = updateData;
    }
}
