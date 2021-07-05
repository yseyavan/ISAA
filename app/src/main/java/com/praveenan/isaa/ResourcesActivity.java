package com.praveenan.isaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ResourcesActivity extends AppCompatActivity {
    private ImageView imageBack;
    CardView moe,nie,edupub,emisnp,ethaksalawa,aki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        moe = findViewById(R.id.moe);
        nie = findViewById(R.id.nie);
        edupub = findViewById(R.id.edupub);
        emisnp = findViewById(R.id.emisnp);
        ethaksalawa = findViewById(R.id.ethaksalawa);
        aki = findViewById(R.id.aki);

        moe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://moe.gov.lk/"));
                startActivity(browserIntent);
            }
        });

        nie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nie.lk/"));
                startActivity(browserIntent);
            }
        });

        edupub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.edupub.gov.lk/"));
                startActivity(browserIntent);
            }
        });

        emisnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.edudept.np.gov.lk/"));
                startActivity(browserIntent);
            }
        });

        ethaksalawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.e-thaksalawa.moe.gov.lk/"));
                startActivity(browserIntent);
            }
        });

        aki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.aki.coach/"));
                startActivity(browserIntent);
            }
        });
    }
}