package com.huan.dagger2demo.utils.multitype.linker;

import android.support.annotation.NonNull;

import com.huan.dagger2demo.utils.multitype.ItemViewBinder;
import com.huan.dagger2demo.utils.multitype.MultiTypeAdapter;

import static com.huan.dagger2demo.utils.multitype.Preconditions.checkNotNull;

/**
 * Created by huanshao on 2018/7/4.
 */

public class OneToMoreBuilder<T> {

    private final MultiTypeAdapter adapter;

    private final Class<? extends T> clazz;

    private ItemViewBinder<T, ?>[] holders;

    public OneToMoreBuilder(@NonNull MultiTypeAdapter adapter, Class<? extends T> clazz) {
        this.adapter = adapter;
        this.clazz = clazz;
    }

    public OneToMoreBuilder<T> to(ItemViewBinder<T, ?>[] holders) {
        checkNotNull(holders);
        this.holders = holders;
        return this;
    }

    public void withLink(@NonNull Link<T> link) {
        checkNotNull(link);
        doRegister(link);
    }

    private void doRegister(Link<T> link) {
        for (ItemViewBinder<T, ?> holder : holders) {
            if (adapter != null) {
                adapter.register(clazz, holder, link);
            }
        }
    }
}


