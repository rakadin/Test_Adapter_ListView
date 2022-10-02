package com.example.test_adapter_listview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.test_adapter_listview.Adapters.Personal_Adapter;
import com.example.test_adapter_listview.Subjects.Person;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    EditText name,CMND;
    RadioButton rab1,rab2,rab3;
    CheckBox chk1,chk2,chk3;
    String temp,temp2;
    Button upBut;
    ArrayList<Person> listProduct = new ArrayList<Person>();
    ArrayList<String> spinnerList = new ArrayList<>();
    ArrayAdapter<String> spinnerAdpt = null;
    Personal_Adapter personal_adapter;
    ListView listViewPerson;
    Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp2 ="";
        // get ids
        name = findViewById(R.id.nameInsert);
        CMND = findViewById(R.id.CMND);
        rab1 = findViewById(R.id.rdb1);
        rab2 = findViewById(R.id.rdb2);
        rab3 = findViewById(R.id.rdb3);
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        upBut = findViewById(R.id.sentBut);
        listViewPerson = findViewById(R.id.lvPerson);
        //
        // spinner chon danh sach
        //
        spinner1 = findViewById(R.id.spinner1);
        // chuẩn bị dữ liệu
        spinnerList.add("Thành phố");
        spinnerList.add("Hà Nội");
        spinnerList.add("Hà Tây");
        spinnerList.add("Hà Thành");
        spinnerList.add("Hà Ngoại");
        spinnerList.add("Hà Lội");
        //set adpter for spinner
        spinnerAdpt = new ArrayAdapter<String>(this, android.support.constraint.R.layout.support_simple_spinner_dropdown_item,spinnerList);
       spinnerAdpt.setDropDownViewResource(android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item);
       spinner1.setAdapter(spinnerAdpt);
        /*
        end spinner
         */


        // khoi tao listperson
        listProduct.add(new Person(0,"Họ tên","CMND","Bằng cấp","Sở thích"));
        personal_adapter = new Personal_Adapter(listProduct);// khoi tao adapter
        listViewPerson.setAdapter(personal_adapter);// set adapter
        // set su kien khi chon radioButton
        rab1.setOnCheckedChangeListener(listenerRadio);
        rab2.setOnCheckedChangeListener(listenerRadio);
        rab3.setOnCheckedChangeListener(listenerRadio);
        // set su kien chi chon checkbox
        chk1.setOnCheckedChangeListener(listenerCheckbox);
        chk2.setOnCheckedChangeListener(listenerCheckbox);
        chk3.setOnCheckedChangeListener(listenerCheckbox);
        upBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int int_random = ThreadLocalRandom.current().nextInt();
                String nameIn = String.valueOf(name.getText());
                String CMNDIn = String.valueOf(CMND.getText());
                Toast.makeText(MainActivity.this,"Bạn đã nhập"+nameIn+""+CMNDIn+temp,Toast.LENGTH_SHORT).show();
                listProduct.add(new Person(int_random,nameIn,CMNDIn,temp,temp2));
                // thong bao la listview thay doi de thay doi du lieu
                personal_adapter.notifyDataSetChanged();
            }
        });
        // su kien click vao item trong listview
        listViewPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // su kien alert dialog
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Delete");
                b.setMessage("Bạn muốn xóa người này ?");
                b.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel(); // huy dialog
                    }
                });
                b.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listProduct.remove(position);
                        personal_adapter.notifyDataSetChanged();
                    }
                });
                b.create().show();// show dialog
            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"Bạn đã chọn "+ spinnerList.get(i),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // set su kien on check cho radio group
    CompoundButton.OnCheckedChangeListener listenerRadio = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b)
            {
                temp = (String) compoundButton.getText();
            }
        }
    };
    // set su kien on check cho checkbox
    CompoundButton.OnCheckedChangeListener listenerCheckbox = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b)
            {
                temp2 += " " + (String) compoundButton.getText();
            }
        }
    };
}