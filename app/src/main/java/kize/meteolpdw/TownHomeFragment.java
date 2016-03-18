package kize.meteolpdw;

/**
 * Created by mavillaz on 17/03/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Url;

public class TownHomeFragment extends Fragment {

    ArrayList<detailCard> detail = new ArrayList<>(5);
    private LinearLayout week;
    private RelativeLayout resume;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle
            savedInstanceState) {
        this.week = (LinearLayout) view.findViewById(R.id.week);
        this.resume = (RelativeLayout) view.findViewById(R.id.resume);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TextView tv1 = (TextView) resume.findViewById(R.id.textDay);


        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("appid","e287f19ce07cdfc1c928f1e4c987a557").build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(
                        JacksonConverterFactory.create())
                .client(client)
                .build();

        API api = retrofit.create(API.class);
        Call<Item> call = api.getLastMessage("Paris");
        call.enqueue(new Callback<Item>() {
                         @Override
                         public void onResponse(Call<Item> call, retrofit2.Response<Item> response) {
                             Item body = response.body();
                             tv1.setText(body.name);
                         }

                         @Override
                         public void onFailure(Call<Item> call, Throwable t) {
                             Log.d("TAG", "onFailure: ");
                         }
        });


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE", Locale.getDefault());

        for(int i = 0; i < week.getChildCount(); i++){
            View v = week.getChildAt(i);
            TextView tv = (TextView) v.findViewById(R.id.textDay);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String format = simpleDateFormat.format(calendar.getTimeInMillis());
            tv.setText(format.toUpperCase());
        }

        String appName = getResources().getString(R.string.app_name);
        detail.add(new detailCard("Paris","Il fait chaud", "ic_weather_snow", -1, -3, 20));
        detail.add(new detailCard("Paris","Inondation !", "ic_weather_clouds", 4, 6, 12));
        detail.add(new detailCard("Paris","Tempête de sable sur Paris", "ic_weather_hail", 4, 23, 75));
        detail.add(new detailCard("Paris","Il fera nuit toute la journée", "ic_weather_haze", 12, 1, 100));

        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("detail", detail);
                startActivity(intent);
            }

        });
    }
}
