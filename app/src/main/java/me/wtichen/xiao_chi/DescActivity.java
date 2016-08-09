package me.wtichen.xiao_chi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class DescActivity extends AppCompatActivity {

    private TextView engName;
    private TextView twName;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        engName = (TextView)super.findViewById(R.id.engName);
        twName = (TextView)super.findViewById(R.id.twName);
        desc = (TextView)super.findViewById(R.id.desc);

        try {
            JSONObject json = new JSONObject(super.getIntent().getStringExtra("jsonData"));
            Iterator<String> iter = json.keys();

            String maxScoreKey = "";
            double maxScore = -1;

            while (iter.hasNext()) {
                String key = iter.next();
                double score = json.getDouble(key);

                if (score > maxScore) {
                    maxScore = score;
                    maxScoreKey = key;
                }
            }

            myLog("Prediction Result");
            myLog(maxScoreKey);
            myLog(String.valueOf(maxScore));
            myLog(getTwName(maxScoreKey));
            myLog(getDesc(maxScoreKey));

            engName.setText(maxScoreKey);
            twName.setText(getTwName(maxScoreKey));
            desc.setText(getDesc(maxScoreKey));


        }
        catch (Exception e) {

        }
    }

    protected String getTwName(String engName) {
        String twName = "";

        switch (engName) {
            case "rice tube pudding":
                twName = "筒仔米糕";
                break;
            case "fried sweet potato ball":
                twName = "地瓜球";
                break;
            case "oyster thin noodles":
                twName = "大腸麵線";
                break;
            case "chicken fillet":
                twName = "雞排";
                break;
            case "stinky tofu":
                twName = "臭豆腐";
                break;
            case "oyster omelet":
                twName = "蚵仔煎";
                break;
            case "beef noodles":
                twName = "牛肉麵";
                break;
            case "xiaolongbao":
                twName = "小籠包";
                break;
            case "tofu pudding":
                twName = "豆花";
                break;
            case "sausage with sticky rice":
                twName = "大腸包小腸";
                break;
            case "bubble tea":
                twName = "珍珠奶茶";
                break;
            case "red bean cake":
                twName = "紅豆餅";
                break;
            case "scallion pancake":
                twName = "蔥油餅";
                break;
            case "pan fried bun":
                twName = "水煎包";
                break;
            case "pepper cake":
                twName = "胡椒餅";
                break;
            case "pig blood cake":
                twName = "豬血糕";
                break;
            case "braised pork rice":
                twName = "滷肉飯";
                break;
            case "rice dumpling":
                twName = "粽子";
                break;
            case "steamed sandwich":
                twName = "割包";
                break;
            case "rice pudding":
                twName = "碗粿";
                break;
            case "taiwanese meatball":
                twName = "肉圓";
                break;
        }

        return twName;
    }

    protected String getDesc(String engName) {
        String  desc = "";

        switch (engName) {
            case "rice tube pudding":
                desc = getResources().getString(R.string.rice_pudding_desc);
                break;
            case "fried sweet potato ball":
                desc = getResources().getString(R.string.fried_sweet_potato_ball_desc);
                break;
            case "oyster thin noodles":
                desc = getResources().getString(R.string.oyster_thin_noodles_desc);
                break;
            case "chicken fillet":
                desc = getResources().getString(R.string.chicken_fillet_desc);
                break;
            case "stinky tofu":
                desc = getResources().getString(R.string.stinky_tofu_desc);
                break;
            case "oyster omelet":
                desc = getResources().getString(R.string.oyster_omelet_desc);
                break;
            case "beef noodles":
                desc = getResources().getString(R.string.beef_noodles_desc);
                break;
            case "xiaolongbao":
                desc = getResources().getString(R.string.xiaolongbao_desc);
                break;
            case "tofu pudding":
                desc = getResources().getString(R.string.tofu_pudding_desc);
                break;
            case "sausage with sticky rice":
                desc = getResources().getString(R.string.sausage_with_sticky_rice_desc);
                break;
            case "bubble tea":
                desc = getResources().getString(R.string.bubble_tea_desc);
                break;
            case "red bean cake":
                desc = getResources().getString(R.string.red_bean_cake_desc);
                break;
            case "scallion pancake":
                desc = getResources().getString(R.string.scallion_pancake_desc);
                break;
            case "pan fried bun":
                desc = getResources().getString(R.string.pan_fried_bun_desc);
                break;
            case "pepper cake":
                desc = getResources().getString(R.string.pepper_cake_desc);
                break;
            case "pig blood cake":
                desc = getResources().getString(R.string.pig_blood_cake_desc);
                break;
            case "braised pork rice":
                desc = getResources().getString(R.string.braised_pork_rice_desc);
                break;
            case "rice dumpling":
                desc = getResources().getString(R.string.rice_dumpling_desc);
                break;
            case "steamed sandwich":
                desc = getResources().getString(R.string.steamed_sandwich_desc);
                break;
            case "rice pudding":
                desc = getResources().getString(R.string.rice_pudding_desc);
                break;
            case "taiwanese meatball":
                desc = getResources().getString(R.string.taiwanese_meatball_desc);
                break;
        }

        return desc;
    }

    protected void myLog(String msg) {
        Log.d("wtichen.tw", msg);
    }
}
