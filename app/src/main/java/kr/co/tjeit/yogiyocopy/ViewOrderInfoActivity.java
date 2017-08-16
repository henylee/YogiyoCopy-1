package kr.co.tjeit.yogiyocopy;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

import kr.co.tjeit.yogiyocopy.data.OrderData;
import kr.co.tjeit.yogiyocopy.data.StoreData;
import kr.co.tjeit.yogiyocopy.fragment.StoreListFragment;

public class ViewOrderInfoActivity extends BaseActivity {

    OrderData mOrderdata = null;

    private android.widget.TextView orderStoreNameTxt;
    private android.widget.TextView orderZoneNameTxt;
    private android.widget.TextView orderCostTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_info);

        mOrderdata = (OrderData) getIntent().getSerializableExtra("주문데이터");

        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        orderStoreNameTxt.setText(mOrderdata.getOrderStore().getStoreName());
        orderZoneNameTxt.setText(mOrderdata.getLocation());
        String costStr = String.format(Locale.KOREA, "%,d원", mOrderdata.getCost());
        orderCostTxt.setText(costStr);

    }

    @Override
    public void bindViews() {
        this.orderCostTxt = (TextView) findViewById(R.id.orderCostTxt);
        this.orderZoneNameTxt = (TextView) findViewById(R.id.orderZoneNameTxt);
        this.orderStoreNameTxt = (TextView) findViewById(R.id.orderStoreNameTxt);
    }

}
