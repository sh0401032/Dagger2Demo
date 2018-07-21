package com.huan.dagger2demo.utils.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.huan.dagger2demo.utils.multitype.linker.DefaultLink;
import com.huan.dagger2demo.utils.multitype.linker.Link;
import com.huan.dagger2demo.utils.multitype.linker.OneToMoreBuilder;

import java.util.Collections;
import java.util.List;

import static com.huan.dagger2demo.utils.multitype.Preconditions.checkNotNull;


/**
 * Created by huanshao on 2018/7/4.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private @NonNull
    List<?> items;

    private IType type;

    public MultiTypeAdapter() {
        this(Collections.emptyList());
    }

    public MultiTypeAdapter(@NonNull List<?> items) {
        this(items, new MultiType());
    }

    public MultiTypeAdapter(@NonNull List<?> items, int initialCapacity) {
        this(items, new MultiType(initialCapacity));
    }

    public MultiTypeAdapter(@NonNull List<?> items, @NonNull IType type) {
        checkNotNull(items);
        checkNotNull(type);
        this.items = items;
        this.type = type;
    }

    public void setItems(@NonNull List<?> items) {
        checkNotNull(items);
        this.items = items;
    }

    public List<?> getItems() {
        return items;
    }

    // 一个class对多个viewHolder
    public <T> OneToMoreBuilder register(Class<? extends T> clazz) {
        return new OneToMoreBuilder(this, clazz);
    }

    public <T> void register(Class<? extends T> clazz, ItemViewBinder<T, ?> holder) {
        checkNotNull(clazz);
        checkNotNull(holder);
        register(clazz, holder, new DefaultLink());
    }

    public <T> void register(Class<? extends T> clazz, ItemViewBinder<T, ?> holder, Link<T> link) {
        this.type.register(clazz, holder);
        holder.adapter = this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemViewBinder holder = this.type.getItemViewHolder(viewType);
        holder.mContext = parent.getContext();
        return holder.onCreateViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object item = this.items.get(position);
        ItemViewBinder itemViewBinder = this.type.getItemViewHolder(holder.getItemViewType());
        itemViewBinder.onBinderViewHolder(holder, item);
    }

    @Override
    public int getItemCount() {
        return this.items == null ? 0 : this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = this.items.get(position);
        return indexInTypesOf(position, obj);
    }

    private int indexInTypesOf(int position, @NonNull Object item) throws ViewHolderNotFoundException {
        int index = this.type.firstIndexOf(item.getClass());
        if (index != -1) {
            return index;
        }
        throw new ViewHolderNotFoundException(item.getClass());
    }

}
