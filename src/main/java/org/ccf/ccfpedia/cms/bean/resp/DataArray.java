package org.ccf.ccfpedia.cms.bean.resp;

import java.util.List;

public class DataArray<T> {
    private int count;
    private List<T> array;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getArray() {
        return array;
    }

    public void setArray(List<T> array) {
        this.array = array;
    }
}
