package com.tangyucheng.api;

import retrofit2.Call;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tangyucheng.model.Currency;
import com.tangyucheng.model.Post;

import java.lang.annotation.Retention;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    Gson  gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();



     ApiService apiService = new Retrofit.Builder()
             .baseUrl("http://apilayer.net/")
             .addConverterFactory(GsonConverterFactory.create(gson))
             .build()
             .create(ApiService.class);

    @GET("api/live")
    Call<Currency> convertUsdToVndMap(@QueryMap Map<String,String> options);

     @GET("api/live")
     Call<Currency> convertUsdToVnd(@Query("access_key") String access_key,
                                    @Query("currencies") String currencies,
                                    @Query("source") String source,
                                    @Query("format") Integer format
     );

     @GET("api/users/list")
     Call<Currency> getListUser();

     @GET("api/group/{id}/users")
     Call<Currency> getListUserFromGroup(@Path("id") int groupId) ;

     @GET("api/group/{id}/users")
     Call<Currency> getListUserFromGroupAll(@Path("id") int groupdId,
                                            @Query("sort") String sort);

    @POST("posts")
     Call<Post> sendPosts(@Body Post post);
}
