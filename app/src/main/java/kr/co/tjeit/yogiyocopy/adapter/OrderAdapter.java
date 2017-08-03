package kr.co.tjeit.yogiyocopy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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

        OrderData data = mList.get(position);

        TextView storeNameTxt = (TextView) row.findViewById(R.id.storeNameTxt);
        storeNameTxt.setText(data.getOrderStore().getStoreName());

        TextView dateTxt = (TextView) row.findViewById(R.id.dateTxt);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 dd일");
        dateTxt.setText(sdf.format(data.getOrderDate().getTime()));

        TextView locationTxt = (TextView) row.findViewById(R.id.locationTxt);
        locationTxt.setText(data.getLocation());

        TextView amountTxt = (TextView) row.findViewById(R.id.amountTxt);
        String amountStr = String.format(Locale.KOREA, "%,d 원", data.getCost());
        amountTxt.setText(amountStr);
        // DecimalFormater
        // NumberFormater
        // String.format => 채택 => %d 10진수. %,d 세자리 끊어주기

        return row;
    }
}
