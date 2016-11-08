package nakthon.soraya.driverry;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends FragmentActivity implements OnMapReadyCallback {
    //Explicit
    private GoogleMap mMap;
    private TextView nameTextView, phoneTextView, dateTextView, timeTextView;
    private ImageView imageView;
    private Button button;
    private String[] loginStrings;
    private MyConstant myConstant;
    private String[] jobString;
    private String phoneString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_service_layout);

        //Bind WIdget
        nameTextView = (TextView) findViewById(R.id.textView3);
        phoneTextView = (TextView) findViewById(R.id.textView4);
        dateTextView = (TextView) findViewById(R.id.textView5);
        timeTextView = (TextView) findViewById(R.id.textView6);
        imageView = (ImageView) findViewById(R.id.imageView2);
        button = (Button) findViewById(R.id.button2);

        //Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");
        Log.d("7novV1", "id_Passenger ==>" + loginStrings[0]);

        //Get Value From JSON
        myConstant = new MyConstant();
        GetJob getJob = new GetJob(ServiceActivity.this);
        getJob.execute(myConstant.getUrlGetJobWhereID());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }   //Main Method

    private class GetJob extends AsyncTask<String, Void, String> {


        //Explicit
        private Context context;

        public GetJob(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("ID_passenger", loginStrings[0])
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strings[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }   //doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("7novV1", "Result ==>" + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                String[] columnStrings = myConstant.getJobStrings();

                jobString = new String[columnStrings.length];

                for (int i = 0; i < columnStrings.length; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    jobString[i] = jsonObject.getString(columnStrings[i]);
                    Log.d("7novV2", "jobString(" + i + ") ==> " + jobString[i]);

                }   // for

                //Show Text
                GetPassenger getPassenger = new GetPassenger(context, jobString);
                getPassenger.execute(myConstant.getUrlGetPassengerWhereID());




            }catch (Exception e){
                Log.d("7novV2", "e ==>" + e.toString());
            }

        }   // onPost

    }   //GetJob Class


    private class GetPassenger extends AsyncTask<String, Void, String> {

        //Explicit
        private Context context;
        private String[] resultStrings;

        public GetPassenger(Context context, String[] resultStrings) {
            this.context = context;
            this.resultStrings = resultStrings;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("id", resultStrings[1])
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strings[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch ( Exception e){
                Log.d("7novV3", "e ==>" + e.toString());
                return null;
            }

        }   //doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("7novV3", "Passenger ==>" + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                nameTextView.setText(jsonObject.getString("Name"));
                phoneString = jsonObject.getString("Phone");
                phoneTextView.setText("Phone = " + phoneString);
                dateTextView.setText("วันที่ไปรัย = " + jobString[4]);
                timeTextView.setText("เวลาที่ไปรับ = " + jobString [5]);



            } catch (Exception e) {
                Log.d("7novV3", "e onPost ==> " + e.toString());
            }


        }   // onPost
    } // GetPassenger Class




       @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }  //onMapReady

}  //Main Class
