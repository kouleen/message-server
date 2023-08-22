package com.kouleen.message.api.infrastructure.core;

import java.util.Collections;
import java.util.List;

/**
 * @author zhangqing
 * @since 2023/8/10 16:49
 */
public class IPage<T> {

    protected List<T> records = Collections.emptyList();

    protected long total = 0;

    protected long size = 10;

    protected long current = 1;


    public IPage() {
    }


    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }
}
