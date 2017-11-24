package com.nbgc.resume_builder.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.resume_builder.R;
import com.nbgc.resume_builder.Data.Personalia;
import com.nbgc.resume_builder.Fragments.ExitDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class personalActivity extends AppCompatActivity {

    @BindViews({R.id.personal, R.id.objective})
    List<TextView> textView;
    @BindViews({R.id.name, R.id.email, R.id.phone, R.id.address, R.id.objectivetext})
    List<TextInputEditText> editTexts;
    Date selectedDate;
    @BindView(R.id.dobText)
    TextInputEditText dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
        new fontSelector(this);
        textView.get(0).setTypeface(fontSelector.font);
        textView.get(1).setTypeface(fontSelector.font);
        editTexts.get(4).setTypeface(fontSelector.font);
    }

    @OnClick(R.id.dobText)
    public void chooseDate() {
        int day, year, month;
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        if (selectedDate != null) {
            day = selectedDate.getDate();
            year = selectedDate.getYear() + 1900;
            month = selectedDate.getMonth();
        } else {
            year = calendar.get(Calendar.YEAR) - 22;
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.clear();
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.YEAR, year);
                selectedDate = calendar.getTime();
                SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                dob.setText(format.format(selectedDate));
            }
        }, year, month, day);
        dialog.show();
    }

    @OnClick(R.id.submitButton)
    public void submitData() {
        Personalia.name = editTexts.get(0).getText().toString();
        Personalia.email = editTexts.get(1).getText().toString();
        Personalia.phone = editTexts.get(2).getText().toString();
        Personalia.address = editTexts.get(3).getText().toString();
        Personalia.dateOfBirth = dob.getText().toString();
        Personalia.objective = editTexts.get(4).getText().toString();
        Intent intent = new Intent(this, personalQualityActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new ExitDialogFragment().show(getSupportFragmentManager(), "Exit");
    }

}
