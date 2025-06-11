package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ousltg.travelguide10.Database.DatabaseHelper;
import com.ousltg.travelguide10.R;

public class NoteTodoActivity extends AppCompatActivity {
    //Define Edit Texts and Buttons
    DatabaseHelper myDb;
    EditText editList1, editList2, editId,updateLine;
    Button btnAddData, btnviewAll, btnUpdateData, btnDeleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_todo);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //creating instance of the database helper and assigning variable my db
        myDb = new DatabaseHelper(this);

        editList1 = findViewById(R.id.editTextTextList1);
        editList2 = findViewById(R.id.editTextTextList2);
        editId = findViewById(R.id.editTextTextupdate);
        updateLine = findViewById(R.id.editTextTextupdate);


        btnAddData = findViewById(R.id.btnadd);
        btnviewAll = findViewById(R.id.btnview);
        btnUpdateData = findViewById(R.id.btnupdate);
        btnDeleteData = findViewById(R.id.btndelete);

        //Calling methods
        addData();
        viewAll();
        updateData();
        deleteData();
    }

    //Implement addData() Method and
    // Implement onClickListenermethod to handle INSERT DATA button functionality
    public void addData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editList1.getText().toString(), editList2.getText().toString());
                if (isInserted == true)
                    Toast.makeText(NoteTodoActivity.this, "Add TO DO List Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(NoteTodoActivity.this, "To Do List Not Add Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }

    //Implement viewAll() Method
    //Implement onClickListener method to handle VIEW DATA button functionality

    public void viewAll() {
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor results = myDb.getAllData();
                if (results.getCount() == 0) {
                    showMessage("Error Message:", "No_data_available_in_the_database");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (results.moveToNext()) {
                    buffer.append("ID:" + results.getString(0) + "\n");
                    buffer.append("TITLE:" + results.getString(1) + "\n");
                    buffer.append("LIST:" + results.getString(2) + "\n\n\n");

                    showMessage("View List", buffer.toString());
                }
            }
        });
    }

    //Alert Dialog Box
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    //Implement updateData() Method
    //implement onClickListener method to handle UPDATE DATA button functionality
    public void updateData() {
        btnUpdateData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editId.getText().toString(), editList1.getText().toString(),
                                editList2.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(NoteTodoActivity.this, "Update Data", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(NoteTodoActivity.this, "Data not Update", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    //Implement deleteData() method
    //Implement onClickListener method to handle DELETE DATA button functionality.
    public void deleteData() {
        btnDeleteData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteData = myDb.deleteData(updateLine.getText().toString());
                        if (deleteData >0)
                            Toast.makeText(NoteTodoActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(NoteTodoActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}