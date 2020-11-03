package com.example.login_formulario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class FormularioActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spCountries, spPositions;
    private EditText strFirstName, strLastName, strStreetAddress, strStreetAddress2,
            strCity, strState_Province, strPostal_ZipCode,
            strEmailAddress, strAreaCode, strPhoneNumber;
    private TextView tvDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int contador; //ID for each resume

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        this.spCountries = findViewById(R.id.spCountries);
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spCountries.setAdapter(adapterCountries);

        this.spPositions = findViewById(R.id.spPositions);
        ArrayAdapter<CharSequence> adapterPositions = ArrayAdapter.createFromResource(this,
                R.array.positions, android.R.layout.simple_spinner_item);
        adapterPositions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spPositions.setAdapter(adapterPositions);

        this.strFirstName = findViewById(R.id.edtFirstName);
        this.strLastName = findViewById(R.id.edtLastName);
        this.strStreetAddress = findViewById(R.id.edtStreetAddress);
        this.strStreetAddress2 = findViewById(R.id.edtStreetAddress2);
        this.strCity = findViewById(R.id.edtCity);
        this.strState_Province = findViewById(R.id.edtState_Province);
        this.strPostal_ZipCode = findViewById(R.id.edtPostal_ZipCode);
        this.strEmailAddress = findViewById(R.id.edtEmailAddress);
        this.strAreaCode = findViewById(R.id.edtAreaCode);
        this.strPhoneNumber = findViewById(R.id.edtPhoneNumber);

        this. tvDate = findViewById(R.id.tvDate);
        this. tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(FormularioActivity.this,
                        R.style.Theme_AppCompat_Light_Dialog,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                dialog.show();
            }
        });

        this. mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = month + "/" + day + "/" + year;
                tvDate.setText(date);
            }
        };

        findViewById(R.id.iv_submit).setOnClickListener(this);
        this.contador = 0;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        startActivity(new Intent(FormularioActivity.this, LoginActivity.class));
                        finish();
                    }
                }).create().show();
    }

    private Resume createResume(){
        return new Resume(
                String.valueOf(this.contador),
                this.strFirstName.getText().toString(),
                this.strLastName.getText().toString(),
                this.strStreetAddress.getText().toString(),
                this.strStreetAddress2.getText().toString(),
                this.strCity.getText().toString(),
                this.strState_Province.getText().toString(),
                this.strPostal_ZipCode.getText().toString(),
                this.spCountries.getSelectedItem().toString(),
                this.strEmailAddress.getText().toString(),
                this.strAreaCode.getText().toString(),
                this.strPhoneNumber.getText().toString(),
                this.spPositions.getSelectedItem().toString(),
                this.tvDate.getText().toString()
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_submit:
                //TODO validacion
                Database.getInstance().addResume(createResume());
                showPopUp();
                this.contador++;
                break;
        }
    }

    private void showPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Context dialogContext = builder.getContext();
        LayoutInflater inflater = LayoutInflater.from(dialogContext);
        View alertView = inflater.inflate(R.layout.popup, null);
        builder.setView(alertView);
        builder.setCancelable(false);

        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startActivity(new Intent(FormularioActivity.this, LoginActivity.class));
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        alertDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT); //Controlling width and height.
    }

}