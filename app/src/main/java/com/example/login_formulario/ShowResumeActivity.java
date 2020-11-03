package com.example.login_formulario;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ShowResumeActivity extends AppCompatActivity {
    private Spinner spCountries, spPositions;
    private EditText strFirstName, strLastName, strStreetAddress, strStreetAddress2,
            strCity, strState_Province, strPostal_ZipCode,
            strEmailAddress, strAreaCode, strPhoneNumber;
    private TextView tvDate;
    private Resume resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        String IDresume = getIntent().getStringExtra("resume_id");
        resume = Database.getInstance().getResumeID(IDresume);

        (findViewById(R.id.uploadResume)).setVisibility(View.GONE);
        (findViewById(R.id.submit)).setVisibility(View.GONE);
        (findViewById(R.id.btnupload)).setVisibility(View.GONE);
        (findViewById(R.id.iv_submit)).setVisibility(View.GONE);


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
        this.spCountries = findViewById(R.id.spCountries);
        this.spPositions = findViewById(R.id.spPositions);

        this.tvDate = findViewById(R.id.tvDate);
        loadResume();
        DisableTxt();
        getCountry();

    }

    public void getCountry() {
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this,
                R.array.countries, R.layout.multiline_spinner_dropdown_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spCountries.setAdapter(adapterCountries);

        this.spPositions = findViewById(R.id.spPositions);
        ArrayAdapter<CharSequence> adapterPositions = ArrayAdapter.createFromResource(this,
                R.array.positions, R.layout.multiline_spinner_dropdown_item);
        adapterPositions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spPositions.setAdapter(adapterPositions);

        spCountries.setSelection(adapterCountries.getPosition(resume.getCountry()));
        spPositions.setSelection(adapterPositions.getPosition(resume.getPositionApplaying()));
        spCountries.setEnabled(false);
        spPositions.setEnabled(false);

    }

    public void loadResume() {
        this.strFirstName.setText(resume.getFirstName());
        this.strLastName.setText(resume.getLastName());
        this.strStreetAddress.setText(resume.getStreetAddress());
        this.strStreetAddress2.setText(resume.getStreetAddress2());
        this.strCity.setText(resume.getCity());
        this.strState_Province.setText(resume.getState_province());
        this.strPostal_ZipCode.setText(resume.getPostal_zipCode());
        this.strEmailAddress.setText(resume.getEmailAddress());
        this.strAreaCode.setText(resume.getAreaCode());
        this.strPhoneNumber.setText(resume.getPhoneNumber());
        this.tvDate.setText(resume.getStartDate());

    }

    public void DisableTxt() {
        this.strFirstName.setEnabled(false);
        this.strLastName.setEnabled(false);
        this.strStreetAddress.setEnabled(false);
        this.strStreetAddress2.setEnabled(false);
        this.strCity.setEnabled(false);
        this.strState_Province.setEnabled(false);
        this.strPostal_ZipCode.setEnabled(false);
        this.strEmailAddress.setEnabled(false);
        this.strAreaCode.setEnabled(false);
        this.strPhoneNumber.setEnabled(false);
    }

}