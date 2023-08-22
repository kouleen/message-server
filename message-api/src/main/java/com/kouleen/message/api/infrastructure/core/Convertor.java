package com.kouleen.message.api.infrastructure.core;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author zhangqing
 * @since 2023/5/30 17:40
 */
public interface Convertor extends Serializable {

    default <T> T convertTo(Class<T> destin) {
        T instance;
        try {
            instance = destin.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getCause());
        }
        BeanUtils.copyProperties(this, instance, destin);
        return instance;
    }

    static <S, T> List<T> convertList(final List<S> listSource, Function<S, T> f) {
        if (listSource == null || listSource.size() == 0) {
            return Collections.emptyList();
        }
        List<T> listBack = new ArrayList<>(listSource.size());
        for (S object : listSource) {
            T instance = f.apply(object);
            listBack.add(instance);
        }
        return listBack;
    }

    static <S extends Convertor, T> List<T> convertList(final List<S> listSource, final Class<T> destin) {
        return convertList(listSource, (s) -> s.convertTo(destin));
    }
}
