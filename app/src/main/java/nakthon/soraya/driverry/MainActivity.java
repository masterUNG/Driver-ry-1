package nakthon.soraya.driverry;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText;
    private Button signInButton, signUpButton;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Windget
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText);
        signInButton = (Button) findViewById(R.id.button);
        signUpButton = (Button) findViewById(R.id.button2);

        //signIn Controller
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //Get Value from Edit Text
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (userString.equals("") || passwordString.equals("")) {
                    //Have Space

                    MyAlert myAlert = new MyAlert(MainActivity.this,
                            R.drawable.bird48, "Have Space",
                            "Please Fill All Every Blank");
                    myAlert.myDialog();

                } else {
                    //No Space
                    Authen authen = new Authen(MainActivity.this);
                    authen.execute("http://swiftcodingthai.com/ry/get_data_ry.php");
                }

            } //onClick
        });



    } // Main Method

    private class Authen extends AsyncTask<String, Void, String> {

        //Explicit
        private Context context;

        public Authen(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(params[0]).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("DriverV1", "e doInBack ==>" + e.toString());
                return null;
            }


        }   //doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("DriverV1", "Json ==>" + s);

        } // onPost

    } //Authen Class

} // Main Class
