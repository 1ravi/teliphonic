package com.example.ravisastry.telephonic;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2,et3,et4,et5;
    Uri u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        et5=(EditText)findViewById(R.id.et5);
    }
    public void sendSms(View v)
    {
        SmsManager smanager=SmsManager.getDefault();
        smanager.sendTextMessage(et1.getText().toString(),null,et2.getText().toString(),null,null);

    }
    public void call(View v)
    {
        Intent i=new Intent();
        i.setAction(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+et1.getText().toString()));
        startActivity(i);

    }

    public void sendmail(View v)
    {
        Intent i=new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL,new String[]{et3.getText().toString()});
        i.putExtra(Intent.EXTRA_SUBJECT,new String[]{et4.getText().toString()});
        i.putExtra(Intent.EXTRA_TEXT,new String[]{et5.getText().toString()});
        i.putExtra(Intent.EXTRA_STREAM,u);
        i.setType("message/rfc822");
        startActivity(i.createChooser(i,"message"));
    }
    public void javamail(View v)
    {
        LongOperation lop=new LongOperation(et3.getText().toString(),et4.getText().toString(),et5.getText().toString());


            //LongOperation lop = new LongOperation("uma.mahesh1247@gmail.com", "hi", "uma");

            lop.execute();
        Toast.makeText(getApplicationContext(),"Mail Sent",Toast.LENGTH_SHORT).show();


    }
    public void attach(View v)
    {
        Intent i=new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("*/*");
        startActivityForResult(i,222);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         u=data.getData();
    }
}
