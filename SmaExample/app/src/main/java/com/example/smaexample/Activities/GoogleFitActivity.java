package com.example.smaexample.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smaexample.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleFitActivity extends AppCompatActivity {
    private final int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 4234;
    private FitnessOptions fitnessOptions;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_fit);

        fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_ACTIVITY_SEGMENT, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_WEIGHT, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_HEART_POINTS, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_SLEEP_SEGMENT, FitnessOptions.ACCESS_READ)
                .build();

        account = GoogleSignIn.getAccountForExtension(this, fitnessOptions);

        if (!hasPermissions()){
            requestPermissions();
        }
    }

    private void requestPermissions() {
        GoogleSignIn.requestPermissions(
                this,
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
                account,
                fitnessOptions);
    }

    private boolean hasPermissions(){
        return GoogleSignIn.hasPermissions(account, fitnessOptions);
    }

    private void accessGoogleFit(){
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        long startTime = cal.getTimeInMillis();

        final DataReadRequest readRequest = new DataReadRequest.Builder()
//                .aggregate(DataType.TYPE_ACTIVITY_SEGMENT)
//                .aggregate(DataType.TYPE_STEP_COUNT_DELTA)
//                .aggregate(DataType.TYPE_WEIGHT)
//                .aggregate(DataType.TYPE_HEART_POINTS)
//                .aggregate(DataType.TYPE_HEART_RATE_BPM)
//                .read(DataType.TYPE_SLEEP_SEGMENT)
//                .read(DataType.TYPE_ACTIVITY_SEGMENT)
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA)
//                .read(DataType.TYPE_WEIGHT)
//                .read(DataType.TYPE_HEART_POINTS)
//                .read(DataType.TYPE_HEART_RATE_BPM)
//                .read(DataType.TYPE_SLEEP_SEGMENT)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build();
        if (account == null){
            account = GoogleSignIn.getAccountForExtension(this, fitnessOptions);
        }

        Fitness.getHistoryClient(this, account)
                .readData(readRequest)
                .addOnSuccessListener(new OnSuccessListener<DataReadResponse>() {
                    @Override
                    public void onSuccess(DataReadResponse dataReadResponse) {
                        readResultedData(dataReadResponse);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int a =0;
                    }
                });
    }

    private void readResultedData(DataReadResponse dataReadResponse) {
        DataSet data = dataReadResponse.getDataSet(DataType.TYPE_STEP_COUNT_DELTA);
        if (!data.isEmpty()){
            List<DataPoint> dataPoints = data.getDataPoints();
            if(dataPoints.size() > 0){
                List<Integer> totalList = new ArrayList<>();
                for(int i = 0; i < dataPoints.size(); i++){
                    totalList.add(data.getDataPoints().get(i).getValue(Field.FIELD_STEPS).asInt());
                }
            }
        }
        if (dataReadResponse.getBuckets().size() > 0) {
            Log.e("History", "Number of buckets: " + dataReadResponse.getBuckets().size());
            for (Bucket bucket : dataReadResponse.getBuckets()) {
                List<DataSet> dataSets = bucket.getDataSets();
                for (DataSet dataSet : dataSets) {
                    showDataSet(dataSet);
                }
            }
        }
        else if (dataReadResponse.getDataSets().size() > 0) {
            Log.e("History", "Number of returned DataSets: " + dataReadResponse.getDataSets().size());
            for (DataSet dataSet : dataReadResponse.getDataSets()) {
                showDataSet(dataSet);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE){
            accessGoogleFit();
        }
    }

    private class ViewWeekStepCountTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            return null;
        }
    }

    private void showDataSet(DataSet dataSet) {
        Log.e("History", "Data returned for Data type: " + dataSet.getDataType().getName());
        DateFormat dateFormat = DateFormat.getDateInstance();
        DateFormat timeFormat = DateFormat.getTimeInstance();

        for (DataPoint dp : dataSet.getDataPoints()) {
            Log.e("History", "Data point:");
            Log.e("History", "\tType: " + dp.getDataType().getName());
            Log.e("History", "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)) + " " + timeFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            Log.e("History", "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)) + " " + timeFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            for(Field field : dp.getDataType().getFields()) {
                Log.e("History", "\tField: " + field.getName() +
                        " Value: " + dp.getValue(field));
            }
        }
    }

    public void onTestOneClick(View view) {
        if (!hasPermissions()){
            requestPermissions();
            return;
        }
        accessGoogleFit();
//        AppExecutors.getInstance().networkIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                ViewWeekStepCountTask viewWeekStepCountTask = new ViewWeekStepCountTask();
//                viewWeekStepCountTask.execute();
//            }
//        });
    }
}