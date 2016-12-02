package nakthon.soraya.driverry;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ConfirmJob extends AppCompatActivity {

    //Explicit
    private String[] loginString;
    private String[] tagStrings = new String[]{"1decV1"};
    private Boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_job);


        //Get Value of Login Pass
        loginString = getIntent().getStringArrayExtra("Login");
        for (int i = 0; i < loginString.length; i++) {
            Log.d(tagStrings[0], "loginString(" + i + ")==>" + loginString[i]);
        }   // for
        //Login Status ==> 1
        editStatus(1);

        //Check Job
        checkJob();

    }   // Main Method

    private void checkJob() {

        //TodoIt
        try {

            MyCheckJob myCheckJob = new MyCheckJob(ConfirmJob.this, loginString[0]);
            myCheckJob.execute();
            String s = myCheckJob.get();
            Log.d("1decV2", "s ==> " + s);
            Log.d("1decV3", "s.leanth ==> " + s. length());
            Log.d("1decV3", "Condition s ==> " + (!s. equals("null")));

            if (!s.equals("null")) {

                if (aBoolean) {
                    aBoolean = false;
                    myNotification();
                }

            } // if

        } catch (Exception e) {
            Log.d("1decV2", "e checkJob ==> " + e.toString());
        }


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkJob();

            }
        }, 1000);

    } // CheckJob

    private void myNotification() {

        Log.d("2decV1", "Notification Work");

        Intent intent = new Intent(ConfirmJob.this, NotificationAlert.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(ConfirmJob.this,
                (int) System.currentTimeMillis(), intent, 0);

        Uri uri = RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND);

        Notification.Builder builder = new Notification.Builder(ConfirmJob.this
        );
        builder.setContentTitle("งานมาใหม่ค่ะ");
        builder.setContentText("กรุณาคลิ๊กที่นี้");
        builder.setSmallIcon(R.drawable.doremon48);
        builder.setSound(uri);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0,notification);

    }   //myNotification

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tagStrings[0], "onStop");

        editStatus(0);

    }

    private void editStatus(int intStatus) {

        try {

            String s = null;
            EditStatusDriver editStatusDriver = new EditStatusDriver(ConfirmJob.this,
                    loginString[0], Integer.toString(intStatus));
            editStatusDriver.execute();

            if (Boolean.parseBoolean(editStatusDriver.get())) {
                s = "Change Status OK";
            } else {
                s = "Cannot Change Status";
            }

            Toast.makeText(ConfirmJob.this, s, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.d(tagStrings[0], "e editstatus ==> " + e.toString());

        }

    }
}   //Main Class
