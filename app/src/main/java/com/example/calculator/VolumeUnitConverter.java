package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.calculator.转换器.NumberSystem;
import com.example.calculator.转换器.VolumeConverter;
import com.example.calculator.转换器.VolumeUnit;

import static com.example.calculator.Converters.lengthConverter;
import static com.example.calculator.Converters.systemConverter;
import static com.example.calculator.Converters.volumeConverter;

public class VolumeUnitConverter extends AppCompatActivity
{

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int itemId = item.getItemId();
        if(itemId == R.id.menuItem_calculator)
        {
            Intent intent = new Intent(VolumeUnitConverter.this, Calculator.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_length)
        {
            Intent intent = new Intent(VolumeUnitConverter.this, LengthUnitConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_volume)
        {

        }
        else if(itemId == R.id.menuItem_numberSystem)
        {
            Intent intent = new Intent(VolumeUnitConverter.this, NumberSystemConverter.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(VolumeUnitConverter.this, Help.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_converter);

        if(volumeConverter == null)
            volumeConverter = new VolumeConverter();

        final TextView inputValue = (TextView)findViewById(R.id.textView_inputValue);
        final TextView outputValue = (TextView)findViewById(R.id.textView_outputValue);
        synchronize(inputValue, outputValue);

        String[] arr = new String[]{"立方厘米（cm³）", "立方米（m³）", "毫升（ml）", "升（l）"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr);
        Spinner spinner_input = (Spinner) findViewById(R.id.spinner_input);
        Spinner spinner_output = (Spinner) findViewById(R.id.spinner_output);
        spinner_input.setAdapter(arrayAdapter);
        spinner_output.setAdapter(arrayAdapter);

        VolumeUnit srcUnit = volumeConverter.getSrcUnit();
        VolumeUnit resUnit = volumeConverter.getResUnit();

        if(srcUnit == VolumeUnit.cm3)
            spinner_input.setSelection(0);
        else if(srcUnit == VolumeUnit.m3)
            spinner_input.setSelection(1);
        else if(srcUnit == VolumeUnit.ml)
            spinner_input.setSelection(2);
        else
            spinner_input.setSelection(3);
        if(resUnit == VolumeUnit.cm3)
            spinner_output.setSelection(0);
        else if(resUnit == VolumeUnit.m3)
            spinner_output.setSelection(1);
        else if(resUnit == VolumeUnit.ml)
            spinner_output.setSelection(2);
        else
            spinner_output.setSelection(3);

        spinner_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                    volumeConverter.setSrcUnit(VolumeUnit.cm3);
                else if(i == 1)
                    volumeConverter.setSrcUnit(VolumeUnit.m3);
                else if(i == 2)
                    volumeConverter.setSrcUnit(VolumeUnit.ml);
                else
                    volumeConverter.setSrcUnit(VolumeUnit.l);
                synchronize(inputValue, outputValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        spinner_output.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                    volumeConverter.setResUnit(VolumeUnit.cm3);
                else if(i == 1)
                    volumeConverter.setResUnit(VolumeUnit.m3);
                else if(i == 2)
                    volumeConverter.setResUnit(VolumeUnit.ml);
                else
                    volumeConverter.setResUnit(VolumeUnit.l);
                synchronize(inputValue, outputValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        initialize(R.id.btn_0, inputValue, outputValue);
        initialize(R.id.btn_1, inputValue, outputValue);
        initialize(R.id.btn_2, inputValue, outputValue);
        initialize(R.id.btn_3, inputValue, outputValue);
        initialize(R.id.btn_4, inputValue, outputValue);
        initialize(R.id.btn_5, inputValue, outputValue);
        initialize(R.id.btn_6, inputValue, outputValue);
        initialize(R.id.btn_7, inputValue, outputValue);
        initialize(R.id.btn_8, inputValue, outputValue);
        initialize(R.id.btn_9, inputValue, outputValue);
        initialize(R.id.btn_dot, inputValue, outputValue);
        Button button = (Button) findViewById(R.id.btn_backspace);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(inputValue.getText().length() > 1)
                {
                    volumeConverter.setSrc(inputValue.getText().toString().substring(0, inputValue.getText().length() - 1));
                    synchronize(inputValue, outputValue);
                }
                else if(inputValue.getText().length() == 1)
                {
                   volumeConverter.setSrc("0");
                   synchronize(inputValue, outputValue);
                }
            }
        });
        button = (Button) findViewById(R.id.btn_clear);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               volumeConverter.setSrc("0");
               synchronize(inputValue, outputValue);
            }
        });

    }

    private void initialize(int id, final TextView inputValue, final TextView outputValue)
    {
        final Button button = (Button) findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer sb = new StringBuffer(inputValue.getText());
                if (sb.toString().equals("0"))
                    sb = new StringBuffer("");
                sb.append(button.getText());
                Log.w("LengthUnitConverter", sb.toString());
                System.out.println(sb.toString());
                volumeConverter.setSrc(sb.toString());
                synchronize(inputValue, outputValue);
            }
        });
    }

    private void synchronize(TextView inputValue, TextView outputValue)
    {
        volumeConverter.calculate();
        inputValue.setText(volumeConverter.getSrc());
        outputValue.setText(volumeConverter.getResult());
    }

}
