package me.wtichen.xiao_chi;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private static final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");
    private static final String API_URL = "http://wtichen.me:8080/xiao-chi-prediction";

    private Button btnCamera;
    private Button btnAnalyze;
    private ImageView imgFood;

    private Bitmap bmpPhoto;
    private byte[] bytePhoto;

    OkHttpClient client = new OkHttpClient();

    public void upload() {

        try {
            RequestBody imageData = RequestBody.create(MEDIA_TYPE_JPEG, bytePhoto);

            RequestBody reqBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", "pred.jpeg", imageData)
                    .build();

            Request req = new Request.Builder()
                    .url(API_URL)
                    .post(reqBody)
                    .build();

            client.newCall(req).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    myLog(e.getClass() + ": " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    try {
                        String jsonData = response.body().string();

                        //JSONObject json = new JSONObject(jsonData);
                        //myLog(json.toString());

                        Intent descIntent = new Intent(MainActivity.this, DescActivity.class);
                        descIntent.putExtra("jsonData", jsonData);
                        startActivity(descIntent);
                    }
                    catch (Exception e) {

                    }
                }
            });
        }
        catch (Exception e) {

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Set UI Component*/
        this.imgFood = (ImageView)super.findViewById(R.id.img_food);
        this.btnCamera = (Button)super.findViewById(R.id.btn_camera);
        this.btnAnalyze = (Button)super.findViewById(R.id.btn_analyze);

        btnCamera.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                myLog("Pressing Camera....");

                Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        btnAnalyze.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                myLog("Send image to server");

                upload();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        myLog("Get image from camera activity");

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            /* Get return image*/
            bmpPhoto = (Bitmap)intent.getExtras().get("data");

            /* Set imageview to picture */
            imgFood.setImageBitmap(bmpPhoto);

            /* Convert bitmap to byte array */
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmpPhoto.compress(Bitmap.CompressFormat.JPEG, 100, stream);  // 100 is quality
            bytePhoto = stream.toByteArray();
        }
    }



    protected void myLog(String msg) {
        Log.d("wtichen.tw", msg);
    }
}
