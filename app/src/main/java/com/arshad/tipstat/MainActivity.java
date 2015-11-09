package com.arshad.tipstat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.lvMainList)
    ListView mainList;

    @InjectView(R.id.loading)
    ProgressBar loading;

    @InjectView(R.id.etSearch)
    EditText filterText;


    private static final String url = "http://tipstat.0x10.info/api/tipstat?type=json&query=list_status";
    private List<MembersData> memberList = new ArrayList<MembersData>();
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        adapter = new CustomListAdapter(this, memberList);
        mainList.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Portfolio.class);
                startActivity(i);
            }
        });

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MembersData data = (MembersData) parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, ContentFull.class);
                i.putExtra("id", data.getId());
                i.putExtra("title", data.getEthinicityString());
                i.putExtra("dob", data.getDob());
                i.putExtra("weight", data.getWeight());
                i.putExtra("height", data.getHeight());
                i.putExtra("veg", data.isVeg());
                i.putExtra("drink", data.isDrink());
                i.putExtra("imgUrl", data.getImage());
                i.putExtra("status", data.getStatus());
                startActivity(i);
            }
        });

        filterText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                adapter.getFilter().filter(cs);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        getFeed();
    }

    public void getFeed() {
        loading.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Response", response.toString());
                        loading.setVisibility(View.GONE);
                        if(response.has("members")) {
                            try {
                                MembersData data;
                                JSONArray jsonArr = response.getJSONArray("members");
                                Log.d("Result", jsonArr.toString());
                                for (int i = 0; i < jsonArr.length(); i++) {
                                    JSONObject obj = jsonArr.getJSONObject(i);
                                    data = new MembersData();
                                    data.setId(obj.getInt("id"));
                                    if(!obj.isNull("dob")) {
                                        data.setDob(obj.getString("dob"));
                                    }
                                    if(!obj.isNull("status")) {
                                        data.setStatus(obj.getString("status"));
                                    }
                                    if(!obj.isNull("ethnicity")) {
                                        data.setEthinicity(obj.getInt("ethnicity"));
                                    }
                                    if(!obj.isNull("weight")) {
                                        data.setWeight(obj.getInt("weight"));
                                    }
                                    if(!obj.isNull("height")) {
                                        data.setHeight(obj.getInt("height"));
                                    }
                                    if(!obj.isNull("is_veg")) {
                                        data.setVeg(obj.getInt("is_veg"));
                                    }
                                    if(!obj.isNull("drink")) {
                                        data.setDrink(obj.getInt("drink"));
                                    }
                                    if(!obj.isNull("image")) {
                                        data.setImage(obj.getString("image"));
                                    }
                                    memberList.add(data);
                                }
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                loading.setVisibility(View.GONE);
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error", "Error: " + error.getMessage());
                loading.setVisibility(View.GONE);
                showToast("Connection Error. Try again.");
            }

        });

        AppController.getInstance().getRequestQueue().add(jsonReq);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
