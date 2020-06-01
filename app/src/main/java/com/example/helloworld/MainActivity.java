package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private StringBuilder bDay = new StringBuilder();
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText inputName;
    private EditText inputEmail;
    private EditText inputUserName;
    private Button inputDate;
    private TextView error;
    private EditText inputOccupation;
    private EditText inputSelfDescription;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.input_field_1);
        inputEmail = findViewById(R.id.input_field_2);
        inputUserName = findViewById(R.id.input_field_3);
        inputDate = findViewById(R.id.button_date);
        error = findViewById(R.id.text_view_8);
        inputOccupation = findViewById(R.id.input_field_4);
        inputSelfDescription = findViewById(R.id.input_field_5);

        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDate.setText(getString(R.string.empty_string));
                bDay.setLength(0);
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        mDateSetListener, year, month, day);
                if(dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                }

                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                StringBuilder theDate = new StringBuilder();
                inputDate.setText(theDate.append(year).append(getString(R.string.slash))
                        .append(month).append(getString(R.string.slash)).append(dayOfMonth).toString());
                bDay.append(year).append(getString(R.string.dash));
                if(month <= 9) {
                    bDay.append(0).append(month);
                } else {
                    bDay.append(month);
                }
                bDay.append(getString(R.string.dash));
                if(dayOfMonth <= 9) {
                    bDay.append(0).append(dayOfMonth);
                } else {
                    bDay.append(dayOfMonth);
                }
            }
        };

        Log.i(TAG, "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");

        inputName.setText(getString(R.string.empty_string));
        inputEmail.setText(getString(R.string.empty_string));
        inputUserName.setText(getString(R.string.empty_string));
        inputDate.setText(R.string.date_of_birth);
        bDay.setLength(0);
        inputOccupation.setText(getString(R.string.empty_string));
        inputSelfDescription.setText(getString(R.string.empty_string));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState()");

        if(savedInstanceState.containsKey(Constants.KEY_BIRTHDATE_BUTTON)) {
            inputDate.setText(savedInstanceState.getString(Constants.KEY_BIRTHDATE_BUTTON));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState()");

        outState.putString(Constants.KEY_BIRTHDATE_BUTTON, inputDate.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    public boolean checkDate() {
        LocalDate today = LocalDate.now();
        Log.i(TAG, bDay.toString());
        String str = bDay.toString();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(getString(R.string.date_formatter_string));
        LocalDate dob;
        if(!str.equals(getString(R.string.empty_string))) {
            dob = LocalDate.parse(str, dateTimeFormatter);
        } else {
            dob = LocalDate.now();
        }

        Period p = Period.between(dob, today);
        int numYears = p.getYears();
        age = Integer.toString(numYears);

        return numYears >= 18;
    }

    public void goToThankYouActivity(View view) {
        Intent thankYouActivity = new Intent(MainActivity.this, ThankYou.class);
        Bundle bundle = new Bundle();
        if(checkDate()) {
            bundle.putString(Constants.KEY_NAME, inputName.getText().toString());
            bundle.putString(Constants.KEY_EMAIL, inputEmail.getText().toString());
            bundle.putString(Constants.KEY_USERNAME, inputUserName.getText().toString());
            bundle.putString(Constants.KEY_DATE, inputDate.getText().toString());
            bundle.putString(Constants.KEY_OCCUPATION, inputOccupation.getText().toString());
            bundle.putString(Constants.KEY_SELF_DESCRIPTION, inputSelfDescription.getText().toString());
            bundle.putString(Constants.KEY_AGE, age);
            thankYouActivity.putExtras(bundle);
            startActivity(thankYouActivity);
        } else {
            error.setText(R.string.sorry_too_young);
        }
    }
}
