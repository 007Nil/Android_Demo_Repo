package com.isabellatechsolutions.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button  btn_add;
    private Button btn_viewAll;
    private EditText et_name;
    private EditText et_age;
    private ListView lv_customerList;

    private ArrayAdapter customerArrayAdapter;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewall);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.num_age);

        lv_customerList = findViewById(R.id.lv_customerlist);

        databaseHelper = new DatabaseHelper(MainActivity.this);
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, databaseHelper.findAll());
        lv_customerList.setAdapter(customerArrayAdapter);

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                List<CustomerModel> allCustomers =  databaseHelper.findAll();

                ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, allCustomers);

                lv_customerList.setAdapter(customerArrayAdapter);

//                Toast.makeText(MainActivity.this, allCustomers.toString(), Toast.LENGTH_SHORT).show();
            }

        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    CustomerModel customerModel = new CustomerModel();

                    customerModel.setCustomerName(et_name.getText().toString());
                    customerModel.setCustomerAge(Integer.parseInt(et_age.getText().toString()));
                    customerModel.setActive(true);
//                Toast.makeText(MainActivity.this,"ADD Clicked", Toast.LENGTH_SHORT).show();
                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                    boolean result = databaseHelper.addOne(customerModel);

                    if (result == true) {
                        Toast.makeText(MainActivity.this, "Successful !!", Toast.LENGTH_SHORT).show();
                        databaseHelper = new DatabaseHelper(MainActivity.this);
                        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, databaseHelper.findAll());
                        lv_customerList.setAdapter(customerArrayAdapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Failed !!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}