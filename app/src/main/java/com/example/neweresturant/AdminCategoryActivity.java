package com.example.neweresturant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView Prizzer,Rice;

    private Button LogoutBtn,maintainProductsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

          LogoutBtn =(Button) findViewById(R.id.admin_logout_btn);
          maintainProductsBtn =(Button) findViewById(R.id.maintain_btn);



          maintainProductsBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent =new Intent(AdminCategoryActivity.this,HomeActivity.class);
                 intent.putExtra("Admin","Admin");
                  startActivity(intent);


              }
          });





        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(AdminCategoryActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        Prizzer =(ImageView) findViewById(R.id.prizzer);
        Rice =(ImageView) findViewById(R.id.rice);

        Prizzer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Prizzer");
                startActivity(intent);


            }
        });
        Rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","Rice");
                startActivity(intent);

            }
        });

    }
}