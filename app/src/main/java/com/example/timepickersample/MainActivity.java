package com.example.timepickersample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private EditText notificationTitle, notificationMessage;
    private Button btnChannel1, btnChannel2;
    private NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        notificationHelper = new NotificationHelper(this);
        notificationTitle = findViewById(R.id.notification_title);
        notificationMessage = findViewById(R.id.notification_message);
        btnChannel1 = findViewById(R.id.btn_chanel1);
        btnChannel2 = findViewById(R.id.btn_chanel2);
        btnChannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnChannel1(notificationTitle.getText().toString(), notificationMessage.getText().toString());
            }
        });
        btnChannel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnChannel2(notificationTitle.getText().toString(), notificationMessage.getText().toString());
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }

    public void sendOnChannel1(String title, String msg){
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification(title, msg);
        notificationHelper.getManager().notify(1, nb.build());
    }
    public void sendOnChannel2(String title, String msg){
        NotificationCompat.Builder nb = notificationHelper.getChannel2Notification(title, msg);
        notificationHelper.getManager().notify(2, nb.build());
    }
}