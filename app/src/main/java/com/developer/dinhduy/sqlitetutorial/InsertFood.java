package com.developer.dinhduy.sqlitetutorial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class InsertFood extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    private EditText editText;
    private int REQUEST_CODE=1998;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_food);
        imageView=(ImageView) findViewById(R.id.choise_image);
        button=(Button) findViewById(R.id.btn_add);
        editText=(EditText) findViewById(R.id.textinput);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Progess_Picture();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK&&data!=null){{

            Uri uri=data.getData();
            imageView.setImageURI(uri);
            Log.d("AAA", "onActivityResult set image complied ");
        }}
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void Progess_Picture(){

        BitmapDrawable drawable= (BitmapDrawable) imageView.getDrawable();

        Bitmap bitmap=drawable.getBitmap();

        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,70,byteArrayOutputStream);

        byte [] bytearr=byteArrayOutputStream.toByteArray();

        String name=editText.getText().toString();

        MainActivity.sqlite.INSERT(name,bytearr);
        Toast.makeText(this, "Add to Sqlite ...", Toast.LENGTH_SHORT).show();
        MainActivity.Getdata();
    }
}
