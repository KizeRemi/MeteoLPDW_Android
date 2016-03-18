package kize.meteolpdw;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by mavillaz on 16/03/2016.
 */

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();
    public static ArrayList<detailCard> detail = new ArrayList<detailCard>();
    LinearLayout daysLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        detail = intent.getParcelableArrayListExtra("detail");
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd/MM/yyyy", Locale.getDefault());

        daysLinear = (LinearLayout) findViewById(R.id.daysLinear);
        for(int i = 0; i < daysLinear.getChildCount(); i++){
            View v = daysLinear.getChildAt(i);
            TextView t1 = (TextView) v.findViewById(R.id.dayText);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String format = simpleDateFormat.format(calendar.getTimeInMillis());
            t1.setText(format.toUpperCase());
            TextView t2 = (TextView) v.findViewById(R.id.resumeText);
            t2.setText(detail.get(i).description);
            TextView t3 = (TextView) v.findViewById(R.id.chanceText);
            t3.setText("Chance of Rain/Snow: "+detail.get(i).chance+"%");
            TextView t4 = (TextView) v.findViewById(R.id.tempText);
            t4.setText(detail.get(i).morning+"°, "+detail.get(i).afternoon+"°");
            ImageView img = (ImageView) v.findViewById(R.id.imageDay);
            img.setImageResource(getResources().getIdentifier(detail.get(i).img, "drawable", getPackageName()));
        }

        Toast toast = Toast.makeText(getApplicationContext(), "test",Toast.LENGTH_SHORT);
        toast.show();
        Log.d(TAG, String.valueOf(getResources().getString(R.string.app_name)));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

