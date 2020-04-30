package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.Activity2;

public class MainActivity extends AppCompatActivity {

    EditText inputName;
    EditText inputEmail;
    EditText inputUserName;
    EditText inputDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.input_field_1);
        inputEmail = findViewById(R.id.input_field_2);
        inputUserName = findViewById(R.id.input_field_3);
        inputDate = findViewById(R.id.input_field_4);
    }

    public void goToThankYouActivity(View view) {
        Intent thankYouActivity = new Intent(MainActivity.this, ThankYou.class);
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.KEY_NAME, inputName.getText());
        startActivity(thankYouActivity);
    }
}
