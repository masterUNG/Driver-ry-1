package nakthon.soraya.driverry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationAlert extends AppCompatActivity {

    //Explicit
    private Button okButton, noButton;
    private String[] loginStrings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_alert);

        loginStrings = getIntent().getStringArrayExtra("Login");

        //Bind Widget
        okButton = (Button) findViewById(R.id.button7);
        noButton = (Button) findViewById(R.id.button8);

        //no Controller
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //ok Controller
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationAlert.this, ServiceActivity.class);
                intent.putExtra("Login", loginStrings);
                startActivity(intent);
                finish();
            }
        });


    }   // Main Class
}   // Main Class
