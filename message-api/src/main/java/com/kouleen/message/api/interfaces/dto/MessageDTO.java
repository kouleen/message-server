package com.kouleen.message.api.interfaces.dto;

import java.io.Serializable;

/**
 * @author zhangqing
 * @since 2023/8/23 12:47
 */
public class MessageDTO<T> implements Serializable {

    private T payload;

    public MessageDTO() {
    }

    public MessageDTO(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public MessageDTO<T> setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public MessageDTO<T> build(){
        return new MessageDTO<T>();
    }
}
