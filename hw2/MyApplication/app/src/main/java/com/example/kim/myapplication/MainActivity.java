package com.example.kim.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;



public class MainActivity extends AppCompatActivity {

    //private ImageView image = (ImageView)findViewById(R.id.img);

    private Mat tmp;
    private Mat edges;
    private Bitmap result;
    private ImageView image;
    private ImageView image2;


    @Override
    public void onCreate(Bundle savedInstanceState) {//need import android os bundle; takes the bundle of this and create activity
        super.onCreate(savedInstanceState);
        if (!OpenCVLoader.initDebug()) {
        }
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void DrawCannyImage(){

        image2 = (ImageView) findViewById(R.id.img2);
        image = (ImageView) findViewById(R.id.img);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lena);
        tmp = new Mat();
        Utils.bitmapToMat(bitmap, tmp);
        edges = new Mat(tmp.size(), CvType.CV_8UC1);
        Imgproc.cvtColor(tmp, edges, Imgproc.COLOR_BGR2GRAY,4);
        Imgproc.GaussianBlur(edges, edges, new Size(7,7), 1.5, 1.5);
        Imgproc.Canny(edges, edges, 80, 100);

        result = Bitmap.createBitmap(edges.cols(), edges.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(edges, result);
        image.setImageBitmap(result);
        image2.setImageBitmap(bitmap);

    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected (int status)
        {
            switch (status)
            {
                case LoaderCallbackInterface.SUCCESS: DrawCannyImage(); break;
                default: super.onManagerConnected(status);
                    break;
            }
        }
    };

}
