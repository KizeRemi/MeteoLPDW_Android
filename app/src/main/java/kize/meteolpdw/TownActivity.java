package kize.meteolpdw;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.util.Log;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class TownActivity extends AppCompatActivity implements ListAdapter.OnClickListener  {
    private static final String TAG = HomeActivity.class.getSimpleName();
    ListAdapter listAdapter;
    DialogInterface dialog;
    ArrayList<Item> item = new ArrayList<>(10);
    private ImageButton save;
    private EditText town;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.town_list);
        save = (ImageButton) findViewById(R.id.addTown);
        town = (EditText) findViewById(R.id.newTown);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(TownActivity.this);
        Set<String> dataTown = prefs.getStringSet("town", new HashSet<String>());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = prefs.edit();
                final Set<String> myTowns = new HashSet<>(prefs.getStringSet("town", new HashSet<String>()));

                myTowns.add(town.getText().toString());
                editor.putStringSet("town", myTowns);
                editor.apply();

                item.add(new Item(town.getText().toString(),"France"));
                listAdapter.notifyDataSetChanged();
            }
        }) ;


        Iterator<String> iterator = dataTown.iterator();
        while (iterator.hasNext()) {
            String text = iterator.next();
            item.add(new Item(text,"France"));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter =new ListAdapter(item, this);
        recyclerView.setAdapter(listAdapter);
        Log.d(TAG, String.valueOf(getResources().getString(R.string.app_name)));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(final int position, final Item item) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Suppression d'une ville");

        alertDialogBuilder
                .setMessage("Voulez-vous supprimer cette ville?")
                .setCancelable(false)
                .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        listAdapter.removeTown(position);
                    }
                })
                .setNegativeButton("Non",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
