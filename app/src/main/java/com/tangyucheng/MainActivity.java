package com.tangyucheng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tangyucheng.api.ApiService;
import com.tangyucheng.model.Currency;
import com.tangyucheng.model.Post;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  MainActivity extends AppCompatActivity {

    private TextView tvTerms;
    private TextView tvSource;
    private TextView tvUsdVnd;
    private Button btnCallApi;
    private TextView tvPostResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvent();
//        Job job = new Job(1,"Coder");
//        List<Favorite> favoriteList = new ArrayList<Favorite>();
//
//        favoriteList.add(new Favorite(1,"Music"));
//        favoriteList.add(new Favorite(2,"Read Book"));
//
//        User user = new User(1,"Thanh",true,job,favoriteList);
//
//        Gson gson = new Gson();
//        String strJSon =  gson.toJson(user);
//        Log.e("JSON",strJSon);



    }

    private void addEvent() {
        btnCallApi.setOnClickListener(v -> {
//             clickCallApi();
            sendPosts();
        });
    }

    private void clickCallApi() {
//        http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1

        Map<String,String> options = new HashMap<>();
        options.put("accesss_key","843d4d34ae72b3882e3db642c51e28e6");
        options.put("currencies","VND");
        options.put("source","USD");
        options.put("format","1");



//        ApiService.apiService.convertUsdToVnd("843d4d34ae72b3882e3db642c51e28e6","VND","USD",1)
        ApiService.apiService.convertUsdToVndMap(options)
                .enqueue(
                new Callback<Currency>() {
                    @Override
                    public void onResponse(Call<Currency> call, Response<Currency> response) {
                        Toast.makeText(MainActivity.this,"Call API Success", Toast.LENGTH_LONG).show();
                        Currency currency = response.body();
                        if (currency!=null && currency.isSuccess()){
                             tvSource.setText(currency.getSource());
                            tvTerms.setText(currency.getTerms());
                            tvUsdVnd.setText(String.valueOf(currency.getQuotes().getUSDVND()));

                        }

                    }

                    @Override
                    public void onFailure(Call<Currency> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Call API Error", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void addControls() {
        tvTerms = findViewById(R.id.tv_term);
        tvSource = findViewById(R.id.tv_source);
        tvUsdVnd = findViewById(R.id.tv_usd_vnd);
        tvPostResult = findViewById(R.id.tv_post_result);

        btnCallApi= findViewById(R.id.btn_call_api);

    }
    private void sendPosts(){
        Post post = new Post(10,101,"Thanh","TangYuCheng");
        ApiService.apiService.sendPosts(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(MainActivity.this,"Call API Success", Toast.LENGTH_LONG).show();

                Post postResult = response.body();
                if (response!=null){
                    tvPostResult.setText(postResult.toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
}