package kr.co.tjeit.yogiyocopy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import kr.co.tjeit.yogiyocopy.R;
import kr.co.tjeit.yogiyocopy.data.MenuData;

/**
 * Created by the on 2017-08-16.
 */

public class MenuAdpater extends ArrayAdapter<MenuData> {

    Context mContext;
    List<MenuData> mList;
    LayoutInflater inf;

    public MenuAdpater(Context context, List<MenuData> list) {

        super(context, R.layout.tab_menu_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row==null) {
            row = inf.inflate(R.layout.tab_menu_list_item, null);
        }

        MenuData data = mList.get(position);

        ImageView menuImgView = (ImageView) row.findViewById(R.id.menuImgView);
        TextView menuNameTxt = (TextView) row.findViewById(R.id.menuNameTxt);
        TextView menuCostTxt = (TextView) row.findViewById(R.id.menuCostTxt);

        Glide.with(mContext).load(data.getImagePath()).into(menuImgView);
        menuNameTxt.setText(data.getMenuName());
        String costStr = String.format(Locale.KOREA, "%,d원", data.getPrice());
        menuCostTxt.setText(costStr);

        return row;
    }

}
