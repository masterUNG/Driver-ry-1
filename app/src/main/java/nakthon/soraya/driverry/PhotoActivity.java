package nakthon.soraya.driverry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity {

    //Explicit
    private ImageView takePhotoImage, showPhotoImageView;
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        //Bind Widget
        showPhotoImageView = (ImageView) findViewById(R.id.imageView4);
        takePhotoImage = (ImageView) findViewById(R.id.imageView3);

        //takePhoto Controller
        takePhotoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 0);

            }   // onclick
        });


    }   //Main Method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 0) && (resultCode == RESULT_OK)) {

            Log.d("8novV4", "Take Photo OK ");

            uri = data.getData();

            try {
                Bitmap bitmap = BitmapFactory
                        .decodeStream(getContentResolver()
                                .openInputStream(uri));
                showPhotoImageView.setImageBitmap(bitmap);


            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // if

    }   // onActivityResult
}   //Main Class
