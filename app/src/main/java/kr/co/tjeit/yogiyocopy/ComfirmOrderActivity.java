package kr.co.tjeit.yogiyocopy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.CountedCompleter;

import kr.co.tjeit.yogiyocopy.data.MenuData;

public class ComfirmOrderActivity extends BaseActivity {

    private android.widget.TextView menuTxt;
    private android.widget.Button minusBtn;
    private android.widget.Button plusBtn;
    private TextView costTxt;
    private TextView priceTxt;
    private TextView countNumTxt;

    MenuData mMenuData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_order);
        mMenuData = (MenuData) getIntent().getSerializableExtra("메뉴데이터");

        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prStr = countNumTxt.getText().toString();
                int count = Integer.parseInt(prStr);
                count++;
                countNumTxt.setText(String.format(Locale.KOREA, "%d", count));

                int amount = count * mMenuData.getPrice();
                String amountStr = String.format(Locale.KOREA, "%,d원", amount);
                priceTxt.setText(amountStr);
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prStr = countNumTxt.getText().toString();
                int count = Integer.parseInt(prStr);

                if (count>1) {
                    count--;
                    countNumTxt.setText(String.format(Locale.KOREA, "%d", count));
                    int amount = count * mMenuData.getPrice();
                    String amountStr = String.format(Locale.KOREA, "%,d원", amount);
                    priceTxt.setText(amountStr);
                }

            }
        });

    }

    @Override
    public void setValues() {

        menuTxt.setText(mMenuData.getMenuName());
        String coststr = String.format(Locale.KOREA, "%,d원", mMenuData.getPrice());
        costTxt.setText(coststr);
        priceTxt.setText(coststr);
    }

    @Override
    public void bindViews() {
        this.priceTxt = (TextView) findViewById(R.id.priceTxt);
        this.plusBtn = (Button) findViewById(R.id.plusBtn);
        this.countNumTxt = (TextView) findViewById(R.id.countNumTxt);
        this.minusBtn = (Button) findViewById(R.id.minusBtn);
        this.costTxt = (TextView) findViewById(R.id.costTxt);
        this.menuTxt = (TextView) findViewById(R.id.menuTxt);

    }
}
