package com.arshad.tipstat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Portfolio extends AppCompatActivity {

    private ListView list;
    private PortfolioAdapter adapter;
    private List<PortfolioData> pList = new ArrayList<PortfolioData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio);

        getSupportActionBar().setTitle("My Apps");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        list = (ListView) findViewById(R.id.list);

        pList.add(new PortfolioData(R.drawable.ic_1, "Big Bang Theory Meme", "http://kaspat.com/apps/BigBangTheory/"));
        pList.add(new PortfolioData(R.drawable.ic_2, "GeekShare", "http://kaspat.com/apps/GeekShare/"));
        pList.add(new PortfolioData(R.drawable.ic_3, "Jugaad", "http://kaspat.com/apps/Jugaad/"));
        pList.add(new PortfolioData(R.drawable.ic_4, "KaunDay (Smart Calendar)", "http://kaspat.com/apps/KaunDay/"));
        pList.add(new PortfolioData(R.drawable.ic_5, "QuizMania", "http://kaspat.com/apps/QuizMania/"));
        pList.add(new PortfolioData(R.drawable.ic_6, "RootChecker", "http://kaspat.com/apps/RootChecker/"));
        pList.add(new PortfolioData(R.drawable.ic_7, "Save the bird", "http://kaspat.com/apps/SavetheBird/"));
        pList.add(new PortfolioData(R.drawable.ic_8, "Screen Lock Remover", "http://kaspat.com/apps/ScreenLockRemover/"));
        pList.add(new PortfolioData(R.drawable.ic_9, "Toss Decide", "http://kaspat.com/apps/TossDecide/"));
        pList.add(new PortfolioData(R.drawable.ic_10, "Offline Train Time Table", "http://kaspat.com/apps/TrainTimeTable/"));

        adapter = new PortfolioAdapter(this, pList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pList.get(position).getUrl()));
                startActivity(browserIntent);
            }
        });

    }
}
