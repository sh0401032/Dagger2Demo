package com.huan.dagger2demo.utils.multitype;

import android.support.annotation.NonNull;

/**
 * Created by huanshao on 2018/7/4.
 */

public interface IType {

    <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> holder);

    boolean unregister(@NonNull Class<?> clazz);

    int size();

    int firstIndexOf(Class<?> clazz);

    @NonNull
    Class<?> getClass(int index);

    @NonNull
    ItemViewBinder<?, ?> getItemViewHolder(int index);

}
