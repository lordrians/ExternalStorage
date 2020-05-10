package com.example.externalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText et_filename, et_data;
    Button btn_saved, btn_readed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_filename = findViewById(R.id.et_file_name);
        et_data = findViewById(R.id.et_data);
        btn_saved = findViewById(R.id.btn_save);
        btn_readed = findViewById(R.id.btn_read);
        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = et_filename.getText().toString();
                String data = et_data.getText().toString();

                File myDocsPath = new File("/sdcard/mydocs");
                myDocsPath.mkdirs();

                try {

                    File myFile = new File("/sdcard/mydocs/"+ fileName);
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(data);
                    myOutWriter.close();
                    fOut.close();
                    Toast.makeText(getApplicationContext(), fileName + "Saved", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage().toString()+"A", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_readed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = et_filename.getText().toString();
                String aDataRow = "";
                String aBuffer = "";


                try {
                    File myFile = new File("/sdcard/myDocs/" + filename);
                    FileInputStream fileInputStream = new FileInputStream(myFile);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

                    while ((aDataRow = bufferedReader.readLine()) != null){
                        aBuffer += aDataRow + "\n";
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), aBuffer, Toast.LENGTH_LONG).show();

            }
        });
    }
}
