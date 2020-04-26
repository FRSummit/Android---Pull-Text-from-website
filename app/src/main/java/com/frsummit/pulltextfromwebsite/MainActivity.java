package com.frsummit.pulltextfromwebsite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MainActivity extends AppCompatActivity {

    TextView textView, textView2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView.setText("Init Text");

        textView2 = findViewById(R.id.textView2);
        textView2.setText("Init Text 2");

        button = findViewById(R.id.button);
        button.setText("Pull Text");
    }

    public void btnClick(View view) {
        textView.setText("Btn Clicked");
        getBodyText();
    }

    private void getBodyText() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    String url = "https://www.google.com/search?sxsrf=ALeKk00y4Zf1BityFDvj5XpZYU_qc4aO0w:1587305896633&q=Coronavirus+Stats&stick=H4sIAAAAAAAAAONgFuLVT9c3NMwySk6OL8zJUULlPmL05hZ4-eOesJTTpDUnrzHacHEFZ-SXu-aVZJZUCulxsUFZKlyCUqg6NRik-LlQhXh2MXF7pCbmlGQElySWFC9iFXTOL8rPSyzLLCotVgCLAQCnsUMMkAAAAA&sxsrf=ALeKk00y4Zf1BityFDvj5XpZYU_qc4aO0w:1587305896633&biw=1920&bih=937";//your website url
                    Document doc = Jsoup.connect(url).get();
//                    System.out.println(doc);

                    Element body = doc.body();
                    System.out.println(body.getElementsByClass("yeRnY sz9i9").get(0));
                    System.out.println(body.getElementsByClass("yeRnY sz9i9").eachText().get(0));
                    textView.setText(body.getElementsByClass("yeRnY sz9i9").eachText().get(0));
                    builder.append(body.text());

                } catch (Exception e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText(builder.toString());
                    }
                });
            }
        }).start();
    }
}
