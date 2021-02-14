package com.classy.common;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.classy.common.model.Session;
import com.classy.common.room.MyDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;


public abstract class Activity_MapParent extends AppCompatActivity {
    public RelativeLayout mainRL;
    private TextView appTime, garageName, garageAddress, isOpen;
    private ListView carsLV;
    private Session currentSession;
    private final SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm" , Locale.US);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        MyDatabase.initHelper(getApplicationContext());

        mainRL = findViewById(R.id.mainRL);
        appTime = findViewById(R.id.appTime);
        garageName = findViewById(R.id.garageNameTV);
        garageAddress = findViewById(R.id.garageAddressTV);
        isOpen = findViewById(R.id.garageOpenTV);
        carsLV = findViewById(R.id.carsLV);

        getGarage(this);
    }
    protected void getGarage(Context context){
        Server.getGarage(this, garage -> {
            garageName.setText(garage.getName());
            garageAddress.setText(garage.getAddress());
            if (garage.isOpen())
                isOpen.setText("The Garage Shop is Open!");
            else
                isOpen.setText("The Garage Shop is Closed!");

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.list_view, R.id.textView, garage.getCars());
            carsLV.setAdapter(arrayAdapter);
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        currentSession.setEnd(System.currentTimeMillis());
        currentSession.setTotal(currentSession.getEnd() - currentSession.getStart());
        MyDatabase.getInstance().insertSession(currentSession);
        currentSession = null;
    }
    private void bindLastSession(Session session) {

        String  str = session == null? "this is your first use" : format.format(session.getStart());
        appTime.setText(str);

    }

    private void bindTotalTime(long time) {
        appTime.setText(getPrettyTimeSpent(time));

    }

    @Override
    protected void onStart() {
        super.onStart();
        currentSession = new Session();
        currentSession.setStart(System.currentTimeMillis());
        MyDatabase.getInstance().getLastSession(this::bindLastSession);
        MyDatabase.getInstance().getTotalSpendsTime(this::bindTotalTime);
    }
    private String getPrettyTimeSpent(Long time) {

        //milliseconds
        long different = time;

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        StringBuilder sb = new StringBuilder();
        if (elapsedDays != 0)
            sb.append(elapsedDays).append("  days, ");
        if (elapsedHours != 0)

            sb.append(elapsedHours).
                    append("  hours, ");
        if (elapsedMinutes != 0)
            sb.append(elapsedMinutes).append("  minutes, ");
        if (elapsedSeconds != 0)
            sb.append(elapsedSeconds).append(" seconds");

        return sb.toString().isEmpty()? "first use" : sb.toString();
    }
}