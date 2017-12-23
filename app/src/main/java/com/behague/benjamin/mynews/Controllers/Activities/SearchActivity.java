package com.behague.benjamin.mynews.Controllers.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.behague.benjamin.mynews.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_begin_date) EditText beginDate;

    @BindView(R.id.et_end_date) EditText endDate;

    private int mYear, mMonth, mDay;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        this.configureToolbar();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);

        beginDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if(v == beginDate) {
            beginDatePickerDialog();
        } else if(v == endDate) {
            endDatePickerDialog();
        }
    }

    public void beginDatePickerDialog (){
        // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            monthOfYear += 1;
                            String str = dayOfMonth + "/" + monthOfYear + "/" + year;

                            beginDate.setText(str);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
            }

               public void endDatePickerDialog (){
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            monthOfYear += 1;
                            String str = dayOfMonth + "/" + monthOfYear + "/" + year;

                            endDate.setText(str);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
}
