package com.example.txt;
import androidx.appcompat.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num, txt;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = findViewById(R.id.Input_1);
        txt = findViewById(R.id.Input_2);
        button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String no = num.getText().toString();
                //String msg = txt.getText().toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        SendSMS();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }
            }
        });
        }
    private void SendSMS()
    {
        //read the number and the message
        //trim is used to discard white spaces within a text message.

        String number=num.getText().toString();//.trim();
        String message=txt.getText().toString();//.trim();
        //send the message
        //Toast is a small popup notification that is used to display an information about the operation performed in our app. It lasts for a small period of time.
        //LENGTH_LONG ensures toast is displayed for long.
        try{
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(number,null,message,null,null);
            Toast.makeText(this,"Message Sent", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            // printStackTrace is a method used in Java which handles errors and exceptions.
            e.printStackTrace(); // It will pinpoint the exact location of the exception if an error occurs (Line number.)
            Toast.makeText(this, "Message not Sent",Toast.LENGTH_LONG).show();
        }
    }
}

