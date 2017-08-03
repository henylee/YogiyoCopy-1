package kr.co.tjeit.yogiyocopy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import kr.co.tjeit.yogiyocopy.R;
import kr.co.tjeit.yogiyocopy.data.OrderData;

/**
 * Created by user on 2017-08-03.
 */

public class OrderAdapter extends ArrayAdapter<OrderData> {
   // OrderData와, order_list_item을 활용하는
    // 어댑터를 직접 작성.
    // 메인화면 2번째 탭에 리스트가 (내용은 반영하지 않고) 갯수만 나타나게.

    Context mContext;
    List<OrderData> mList;
    LayoutInflater inf;

    public OrderAdapter(Context context, List<OrderData> list) {
        super(context, R.layout.order_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if ( row == null) {
            row = inf.inflate(R.layout.order_list_item, null);
        }

        return row;
    }
}
