package com.example.neweresturant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.neweresturant.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class PaymentActivity extends AppCompatActivity {

    private EditText cardnameEditText, cardnumberEditText, cardexdateEditText, cardsecodeEditText, paytotalamount;
    private Button paynowBtn;

    private String totalAmount = "";
    private String productID="";

    private int overTotalPrice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        productID =getIntent().getStringExtra("pid");

        paynowBtn = (Button) findViewById(R.id.pay_now_btn);
        cardnameEditText = (EditText) findViewById(R.id.payment_card_name);
        cardnumberEditText = (EditText) findViewById(R.id.payment_card_number);
        cardexdateEditText = (EditText) findViewById(R.id.payment_card_exdate);
        cardsecodeEditText = (EditText) findViewById(R.id.payment_card_secode);

        paytotalamount = (EditText) findViewById(R.id.payment_card_total);



        paynowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PaymentActivity.this,EndActivity.class);
                startActivity(intent);

                Check();

            }
        });

//        show.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("neweresturant").child("Payment");
//                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.hasChildren()){
//                            cardnameEditText.setText(dataSnapshot.child("cardname").getValue().toString());
//                            cardnumberEditText.setText(dataSnapshot.child("cardnumber").getValue().toString());
//                            cardexdateEditText.setText(dataSnapshot.child("cardexdate").getValue().toString());
//                            cardsecodeEditText.setText(dataSnapshot.child("cardsecode").getValue().toString());
//                            paytotalamount.setText(dataSnapshot.child("paytotalamount").getValue().toString());
//
//                        }
//                        else
//                        {
//                            Toast.makeText(getApplicationContext(),"No Soure Display",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                   }
//                });

//            }
//        });


    }


    private void Check() {
        if (TextUtils.isEmpty(cardnameEditText.getText().toString())) {
            Toast.makeText(this, "Please enter card name.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardnumberEditText.getText().toString())) {
            Toast.makeText(this, "Please enter card number.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardexdateEditText.getText().toString())) {
            Toast.makeText(this, "Please enter card expiration date.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardsecodeEditText.getText().toString())) {
            Toast.makeText(this, "Please enter card security code.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(paytotalamount.getText().toString())) {
            Toast.makeText(this, "Please enter totalprice.", Toast.LENGTH_SHORT).show();
        } else {
          payOrder();
        }
    }


   private void payOrder() {
        final String saveCurrentDate, saveCurrentTime;
       Calendar calForDate = Calendar.getInstance();
       SimpleDateFormat currenDate = new SimpleDateFormat("MMM dd , yyyy");
       saveCurrentDate=currenDate.format(calForDate.getTime());

        SimpleDateFormat currenTime = new SimpleDateFormat("HH:mm:ss a");
       saveCurrentTime=currenDate.format(calForDate.getTime());

        final DatabaseReference payordersRef = FirebaseDatabase.getInstance().getReference()
               .child("Payment")
               .child(Prevalent.currentOnlineUser.getPhone());

       HashMap<String, Object> payordersMap =new HashMap<>();
        payordersMap.put("totalAmount", totalAmount);
        payordersMap.put("cardname", cardnameEditText.getText().toString());
        payordersMap.put("cardnumber", cardnumberEditText.getText().toString());
        payordersMap.put("cardexdate", cardexdateEditText.getText().toString());
        payordersMap.put("cardsecode", cardsecodeEditText.getText().toString());
        payordersMap.put("paytotalamount", paytotalamount.getText().toString());
        payordersMap.put("date", saveCurrentDate);
       payordersMap.put("time", saveCurrentTime);

        payordersRef.updateChildren(payordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference().child("Pay List")
                            .child("User View")
                            .child(Prevalent.currentOnlineUser.getPhone())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful())
                                    {
                                       Toast.makeText(PaymentActivity.this,"your payment has been successfully..",Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(PaymentActivity.this, EndActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
                }
            }
        });

   }
}