package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ousltg.travelguide10.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Payment_gpay extends AppCompatActivity implements PaymentResultListener {

    private TextInputEditText payAmount;
    private Button paybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gpay);

//        EditText editTextDecimal = findViewById(R.id.amount_edittext);
//
//        String decimalValueString = editTextDecimal.getText().toString();
//        if (!decimalValueString.isEmpty()) {
//            double decimalValue = Double.parseDouble(decimalValueString);
//
//        }*

        payAmount = findViewById(R.id.amount_edittext);
        paybtn = findViewById(R.id.amount_pay);

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String samount = payAmount.getText().toString();
                int amount = Math.round(Float.parseFloat(samount)*100);

                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_WLNLSJOZv8d1MC");

                JSONObject object = new JSONObject();

                try {
                    object.put("name", "developer");
                    object.put("currency", "BDT");
                    object.put("amount", amount);
                    checkout.open(Payment_gpay.this, object);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Completed"+s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed"+s, Toast.LENGTH_SHORT).show();
    }
}