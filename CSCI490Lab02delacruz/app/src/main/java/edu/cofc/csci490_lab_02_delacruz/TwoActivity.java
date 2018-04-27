package edu.cofc.csci490_lab_02_delacruz;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class TwoActivity extends AppCompatActivity {

    private ImageView supermoonImg;
    private ImageView waterfallImg;

    private int imageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        supermoonImg = findViewById(R.id.supermoon);
        waterfallImg = findViewById(R.id.waterfall);

        supermoonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "I clicked supermoon", Toast.LENGTH_SHORT).show();
                imageID = R.drawable.supermoon;
                finish();

            }
        });

        waterfallImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "I clicked waterfall", Toast.LENGTH_SHORT).show();
                imageID = R.drawable.waterfall;
                finish();
            }
        });

    }

    // This is the response to ActivityForResult call
    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("imageID", imageID);

        setResult(RESULT_OK, intent);
        super.finish();
    }

}

