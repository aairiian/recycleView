package com.example.h0per.rwtest.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.h0per.rwtest.R;

import java.util.ArrayList;
import java.util.Collection;

public class MyAdapter extends RecyclerView.Adapter<MyVH> {
    private OnItemSelectListener onItemSelectListener;

    ArrayList<String> fruits = new ArrayList<>();

    public MyAdapter() {
    }

    public MyAdapter(Collection<String> items) {
        this.fruits.addAll(items);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
//            super.setHasStableIds(hasStableIds);
        super.setHasStableIds(true);
        /*
         * Метод указывает на то что есть стабильные идентификаторы элементов, которые не дублируются в списке.
         * если в списке будет 2 apple или тп дублирующихся элементов, значит ставим false, иначе у нас уникальные идентификаторы, ставим true.
         * */
    }

    @Override
    public long getItemId(int position) {
        /*Получим уникальный идентификатор*/
//            return super.getItemId(position);
        return fruits.get(position).hashCode();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
            /* Если у нас в списке будет более чем один тип view (к примеру, первый элемент это текст,
            а второй это фотка). тогда нам нужно указать тип текущего элемента для позиции, обычно это делается так
            if(position == image) {
                return VIEWTYPES.VIEW_IMAGE.type;
            }
            */
    }

    @Override
    public void onViewRecycled(@NonNull MyVH holder) {
        super.onViewRecycled(holder);
        /*
         * Обычно этот метод используем для того чтобы остановить загрузку фотки из веба. К примеру
         * библиотека Picasso имеет метод cancel():
         * */
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        /*
         * Обычно в этом методе вы запускаем какой-нибудь event-listener, к примеру, слушаем появление интернета
         * */
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        /*
         * Обычно в этом методе вы останавливаем какой-нибудь event-listener, запущенный в onAttachedToRecyclerView, чтобы не было мемори ликов.
         * */
    }

    @Override
    public int getItemCount() {
        // КОличество элементов в списке, если нам необходимо добавить новые элементы и сказать адаптеру , что у нас появиллись новые элементы, мы вызываем метод
//            notifyDataSetChanged();  перегрузить контент всех элементов
//            notifyItemChanged(); перегрузить контент определенного элемента
//            notifyItemMoved(); перегрузить контент элементов и  показать анимацию движения айтема
//            notifyItemRangeChanged(); перегрузить контент области элементов к примеру с 5 по 10 элемент
//            notifyItemRemoved(); показать анимацию и удалить элемент
//            notifyItemInserted(); показать анимацию и вставить элемент
        return fruits.size();
    }

    public boolean addAll(Collection<String> items) {
        boolean success = this.fruits.addAll(items);
        notifyDataSetChanged();
        return success;
    }

    public boolean addElement(String element) {
        int position = fruits.size();
        boolean success = fruits.add(element);
        notifyItemInserted(position);
        return success;
    }

    public void remove(int position) {
        fruits.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    public OnItemSelectListener getOnItemSelectListener() {
        return onItemSelectListener;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new MyVH(
                (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final MyVH myVH, int position) {
        myVH.bind(fruits.get(position));
            /* Когда мы хотим привязать clickListener, нам необходимо точно понимать какой из элементов мы нажимаем,
            для этого нам нужно использовать не position, а myVH.getAdapterPosition() */

        myVH.getParent().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myVH.getAdapterPosition();
                if (onItemSelectListener != null)
                    onItemSelectListener.onItemSelect(position, fruits.get(position));
            }
        });
    }
}