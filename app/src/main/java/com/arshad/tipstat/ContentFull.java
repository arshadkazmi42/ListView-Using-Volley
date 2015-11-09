package com.arshad.tipstat;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ContentFull extends AppCompatActivity {

    @InjectView(R.id.tvHeader)
    TextView tvHeader;

    @InjectView(R.id.ivPicture)
    NetworkImageView ivPicture;

    @InjectView(R.id.tvDob)
    TextView tvDob;

    @InjectView(R.id.tvWeight)
    TextView tvWeight;

    @InjectView(R.id.tvHeight)
    TextView tvHeight;

    @InjectView(R.id.tvVeg)
    TextView tvVeg;

    @InjectView(R.id.tvDrink)
    TextView tvDrink;

    @InjectView(R.id.etStatus)
    EditText etStatus;

    @InjectView(R.id.btSend)
    Button btSend;

    @InjectView(R.id.btShare)
    Button btShare;

    @InjectView(R.id.btBack)
    Button btBack;

    int id;
    private String title, status, dob, weight, height, drink, imgUrl, veg;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_full);
        ButterKnife.inject(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        id = getIntent().getExtras().getInt("id");
        title = getIntent().getExtras().getString("title");
        status = getIntent().getExtras().getString("status");
        dob = getIntent().getExtras().getString("dob");
        weight = getIntent().getExtras().getString("weight");
        height = getIntent().getExtras().getString("height");
        veg = getIntent().getExtras().getString("veg");
        drink = getIntent().getExtras().getString("drink");
        imgUrl = getIntent().getExtras().getString("imgUrl");

        tvHeader.setText(title);
        ivPicture.setImageUrl(imgUrl, imageLoader);
        tvDob.setText("Birthday: " + dob);
        tvWeight.setText("Weight: " + weight + " kg");
        tvHeight.setText("Height: " + height + " cm");
        tvVeg.setText("Is Veg: "+veg);
        if(veg.equals("Yes")) {
            tvVeg.setTextColor(Color.GREEN);
        } else {
            tvVeg.setTextColor(Color.RED);
        }
        tvDrink.setText("Drinks: " + drink);
        if(drink.equals("Yes")) {
            tvDrink.setTextColor(Color.GREEN);
        } else {
            tvDrink.setTextColor(Color.RED);
        }
        etStatus.setText(status);

        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, title);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, status);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + Uri.encode("")));
                intent.putExtra("exit_on_sent", true);
                startActivity(intent);
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
