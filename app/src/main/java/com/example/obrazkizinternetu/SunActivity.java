package com.example.obrazkizinternetu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class SunActivity extends AppCompatActivity {

    private String adr = "https://solarsystem.nasa.gov/system/resources/detail_files/768_oct_1_2015_flare.jpg";
    private Button btn;
    private ImageView img;
    private ProgressBar prgs;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun);
        btn = (Button)findViewById(R.id.btnRETURN);
        img = (ImageView)findViewById(R.id.imgSun);
        prgs = (ProgressBar)findViewById(R.id.progressBarSun);
        txt = (TextView)findViewById(R.id.txtSunInfo);

        ImgTransfer it = new ImgTransfer();
        it.execute(adr);
    }

    public void onReturnClk(View v)
    {
        finish();
    }

    class ImgTransfer extends AsyncTask<String, Void, Boolean>
    {
        private Bitmap bmp;

        @Override
        protected Boolean doInBackground(String... params) {
            URL url;
            InputStream istr;
            boolean bRet = true;
            try
            {
                url = new URL(params[0]);
                istr = url.openStream();
                Bitmap tmp = BitmapFactory.decodeStream(istr);
                bmp = tmp.copy(Bitmap.Config.ARGB_8888, true);
            }
            catch(Exception e)
            {
                bRet = false;
            }
            return bRet;
        }

        @Override
        protected void onPreExecute() {
            prgs.setVisibility(ProgressBar.VISIBLE);
            btn.setEnabled(false);
            txt.setText("Image transfer in progres...");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean bRes) {
            if(bRes)
            {
                Canvas cnv = new Canvas(bmp);
                Paint pen = new Paint();
                int w = bmp.getWidth();
                int h = bmp.getHeight();
                pen.setColor(Color.BLUE);
                pen.setStyle(Paint.Style.STROKE);
                cnv.drawRect(0, 0, w-1, h-1, pen);
                img.setImageBitmap(bmp);
                txt.setText("Transfer completed: img " + w + " x " + h);
            }
            else
                txt.setText("Image Transfer Error!!");

            btn.setEnabled(true);
            prgs.setVisibility(ProgressBar.INVISIBLE);
            super.onPostExecute(bRes);
        }
    }
}



