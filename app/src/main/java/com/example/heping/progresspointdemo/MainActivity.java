package com.example.heping.progresspointdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String[] stages = new String[]{"拆旧", "水电改造", "厨卫墙砖", "中期施工", "木作安排"};
    private static final int progressNodeIndex = 2;
    private ImageView mNodeImage;
    private TextView currentNodeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> list = new LinkedList<>();
        for (String name : stages) {
            list.add(name);
        }
        initConstructionNodeView(list);
    }

    private void initConstructionNodeView(List<String> stages) {
        if (stages == null || stages.isEmpty()) {
            return;
        }
        LinearLayout stageLayout = (LinearLayout) findViewById(R.id.construction_stage_layout);
        for (int i = 0; i < stages.size(); i++) {
            TextView stageTextView = createNodeTextView(i, stages.get(i));
            if (i == progressNodeIndex) {
                currentNodeText = stageTextView;
            }
            stageLayout.addView(stageTextView);
        }
        mNodeImage = (ImageView) findViewById(R.id.point);
        currentNodeText.post(new Runnable() {
            @Override
            public void run() {
                setNodeImageViewParams();
            }
        });
    }

    private void setNodeImageViewParams() {
        int left = currentNodeText.getRight() - (currentNodeText.getWidth() / 2) - (mNodeImage.getWidth() / 2);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mNodeImage.getLayoutParams();
        params.leftMargin = left;
        mNodeImage.setLayoutParams(params);
    }

    private TextView createNodeTextView(int index, String text) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (index > 0) {
            params.leftMargin = dp2px(10);
        }
        textView.setLayoutParams(params);
        textView.setText(text);
        textView.setTextSize(12);
        if (progressNodeIndex >= index) {
            textView.setTextColor(getResources().getColor(R.color.green));
        } else {
            textView.setTextColor(getResources().getColor(R.color.gray));
        }
        return textView;
    }

    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
