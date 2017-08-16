package kr.co.tjeit.yogiyocopy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.tjeit.yogiyocopy.adapter.MenuAdpater;
import kr.co.tjeit.yogiyocopy.data.MenuData;
import kr.co.tjeit.yogiyocopy.data.StoreData;

public class ViewStoreInfoActivity extends BaseActivity {

    StoreData mStoreData = null;
    private android.widget.TextView storeNameTxt;
    private android.widget.ImageView starImg1;
    private android.widget.ImageView starImg2;
    private android.widget.ImageView starImg3;
    private android.widget.ImageView starImg4;
    private android.widget.ImageView starImg5;
    private ImageView storeImg;
    private TextView minDeliveryCostTxt;
    private TextView scoreTxt;
    private ImageView storeImgView;
    private android.widget.TabWidget tabs;
    private android.widget.LinearLayout tab1;
    private android.widget.LinearLayout tab2;
    private android.widget.LinearLayout tab3;
    private android.widget.FrameLayout tabcontent;
    private android.widget.TabHost storeTabHost;
    private TextView minCostTxt;
    private TextView openAndCloseTimeTxt;
    private TextView corpStrTxt;
    private TextView corpNameTxt;

    MenuAdpater menuAdpater;
    List<MenuData> menuDatas = new ArrayList<>();
    ListView menuListView;
    private TextView isCescoImgTxt;
    private ImageView isCescoImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_info);

        mStoreData = (StoreData) getIntent().getSerializableExtra("가게데이터");
        // 이화면에 들어올때는 반드시 가게데이터라고 메모해서 storedata를 객체화하자.

        bindViews();
        makeTabHost();
        setupEvents();
        setValues();
    }

    private void makeTabHost() {

        storeTabHost.setup();

        TabHost.TabSpec spec1 = storeTabHost.newTabSpec("tab1").setIndicator("메뉴");
        spec1.setContent(R.id.tab1);
        storeTabHost.addTab(spec1);

        TabHost.TabSpec spec2 = storeTabHost.newTabSpec("tab2").setIndicator("리뷰");
        spec2.setContent(R.id.tab2);
        storeTabHost.addTab(spec2);

        TabHost.TabSpec spec3 = storeTabHost.newTabSpec("tab3").setIndicator("정보");
        spec3.setContent(R.id.tab3);
        storeTabHost.addTab(spec3);
    }

    @Override
    public void setupEvents() {

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ComfirmOrderActivity.class);
                intent.putExtra("메뉴데이터", mStoreData.getMenuDataList().get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {

        menuAdpater = new MenuAdpater(mContext, mStoreData.getMenuDataList());
        menuListView.setAdapter(menuAdpater);

        storeNameTxt.setText(mStoreData.getStoreName());
        String minStr = String.format(Locale.KOREA, "%,d원", mStoreData.getMinCost());
        minDeliveryCostTxt.setText(minStr);
        scoreTxt.setText(mStoreData.getAvgRating() + "점");

        // 평점에 맞는 별 표시 부분

        // 별 다섯개를 list로 저장해두는 부분
        // list? 별 0~마지막까지 돌면서 하나하나 별모양을 바꿔주려고 하는데,
        // 이를 저장하기에 제일 편한 자료구조가 list임.
        List<ImageView> stars = new ArrayList<>();
        stars.add(starImg1);
        stars.add(starImg2);
        stars.add(starImg3);
        stars.add(starImg4);
        stars.add(starImg5);

        // 모든 별들을 빼내서 회색으로 바꿔주는 반복문.
        // '모든' 걸 빼낼땐, for문의 다른형태로 하는게 편함.
        for (ImageView iv : stars) {
            iv.setImageResource(R.drawable.gray_star);
        }

        // 몇개의 별이 노랗게 되는 구하는 반복문.
        // 단순히 소수점을 버리는것으로 대체.
        // 소수점자리를 버리는 제일 간단한 방법은 int로 캐스팅.
        // for문을 돌때도 int를 쓰는게 편하니깐 int로 캐스팅.
        int rating = (int) mStoreData.getAvgRating();

        // 구해진 노랗게 변할 별의 갯수판정
        // 실제로 별을 칠해주는 부분.
        // 노란별은 끝까지 칠해지지 않는 경우도 있음.
        // 그러므로 전통적인 int i를 이용한 for문을 돌림.
        for (int i = 0; i < rating; i++) {
            stars.get(i).setImageResource(R.drawable.fill_star);
        }

        // 가게의 프로필 사진을 표시하는 부분,
        Glide.with(mContext).load(mStoreData.getImagePath()).into(storeImgView);

        minCostTxt.setText(minStr);

        int openHour = mStoreData.getOpenTime() / 100;
        int openMinute = mStoreData.getOpenTime() % 100;
        int closeHour = mStoreData.getCloseTime() / 100;
        int closeMinute = mStoreData.getCloseTime() % 100;

        String openCloseStr = String.format("%02d:%02d - %02d:%02d", openHour, openMinute, closeHour, closeMinute);
        openAndCloseTimeTxt.setText(openCloseStr);

        if (mStoreData.isCesco()) {
            isCescoImgView.setVisibility(View.VISIBLE);
            isCescoImgTxt.setText("세스코맴버스 사업장");
        }
        else {
            isCescoImgView.setVisibility(View.GONE);
            isCescoImgTxt.setText("-");
        }

        corpNameTxt.setText(mStoreData.getCorpName());
        corpStrTxt.setText(mStoreData.getCorpNumber());

    }

    @Override
    public void bindViews() {
        this.storeTabHost = (TabHost) findViewById(R.id.storeTabHost);
        this.tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        this.tab3 = (LinearLayout) findViewById(R.id.tab3);
        this.corpStrTxt = (TextView) findViewById(R.id.corpStrTxt);
        this.corpNameTxt = (TextView) findViewById(R.id.corpNameTxt);
        this.minCostTxt = (TextView) findViewById(R.id.minCostTxt);
        this.isCescoImgTxt = (TextView) findViewById(R.id.isCescoImgTxt);
        this.isCescoImgView = (ImageView) findViewById(R.id.isCescoImgView);
        this.openAndCloseTimeTxt = (TextView) findViewById(R.id.openAndCloseTimeTxt);
        this.tab2 = (LinearLayout) findViewById(R.id.tab2);
        this.tab1 = (LinearLayout) findViewById(R.id.tab1);
        this.menuListView = (ListView) findViewById(R.id.menuListView);
        this.tabs = (TabWidget) findViewById(android.R.id.tabs);
        this.minDeliveryCostTxt = (TextView) findViewById(R.id.minDeliveryCostTxt);
        this.scoreTxt = (TextView) findViewById(R.id.scoreTxt);
        this.starImg5 = (ImageView) findViewById(R.id.starImg5);
        this.starImg4 = (ImageView) findViewById(R.id.starImg4);
        this.starImg3 = (ImageView) findViewById(R.id.starImg3);
        this.starImg2 = (ImageView) findViewById(R.id.starImg2);
        this.starImg1 = (ImageView) findViewById(R.id.starImg1);
        this.storeImgView = (ImageView) findViewById(R.id.storeImgView);
        this.storeNameTxt = (TextView) findViewById(R.id.storeNameTxt);

    }

}
