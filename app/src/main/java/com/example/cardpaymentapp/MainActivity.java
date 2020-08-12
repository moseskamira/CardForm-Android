package com.example.cardpaymentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class MainActivity extends AppCompatActivity {
    CardForm cardForm;
    Button payBtn;
    AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardForm = findViewById(R.id.card_form);
        payBtn = findViewById(R.id.pay_btn);
        alertBuilder = new AlertDialog.Builder(MainActivity.this);

        createCardForm();
    }

    private void createCardForm() {
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS Required On This Number")
                .setup(MainActivity.this);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInputData();
            }
        });


    }

    private void confirmInputData() {
        if (cardForm.isValid()) {
            alertBuilder.setTitle("Confirm Inputs")
                    .setMessage("CardNumber: "+ cardForm.getCardNumber() + "\n"+
                            "Card Expiry Date: "+ cardForm.getExpirationMonth()+"/ "+ cardForm.getExpirationYear()+ "\n"+
                            "Card CVV: "+ cardForm.getCvv()+ "\n"+
                            "Postal Code: "+ cardForm.getPostalCode()+ "\n"+
                            "Phone Number: "+ cardForm.getMobileNumber()
                            );
            alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Payment Successful", Toast.LENGTH_LONG).show();

                }
            });
            alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "You Canceled Payment", Toast.LENGTH_LONG).show();

                }
            });

            alertBuilder.show();


        }else {
            Toast.makeText(MainActivity.this, "INVALID INPUT", Toast.LENGTH_LONG).show();
        }
    }
}
