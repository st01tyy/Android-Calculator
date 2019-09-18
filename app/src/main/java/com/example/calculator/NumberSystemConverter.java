package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.calculator.转换器.NumberSystem;
import com.example.calculator.转换器.SystemConverter;

import static com.example.calculator.Converters.systemConverter;
import static com.example.calculator.Converters.volumeConverter;

public class NumberSystemConverter extends AppCompatActivity
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
            Intent intent = new Intent(NumberSystemConverter.this, Calculator.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_length)
        {
            Intent intent = new Intent(NumberSystemConverter.this, LengthUnitConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_volume)
        {
            Intent intent = new Intent(NumberSystemConverter.this, VolumeUnitConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_numberSystem)
        {

        }
        else
        {
            Intent intent = new Intent(NumberSystemConverter.this, Help.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_converter);

        final TextView inputValue = (TextView)findViewById(R.id.textView_inputValue);
        final TextView outputValue = (TextView)findViewById(R.id.textView_outputValue);

        if(systemConverter == null)
            systemConverter = new SystemConverter();
        synchronize(inputValue, outputValue);

        String[] arr = new String[]{"二进制", "八进制", "十进制", "十六进制"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr);
        Spinner spinner_input = (Spinner) findViewById(R.id.spinner_input);
        Spinner spinner_output = (Spinner) findViewById(R.id.spinner_output);
        spinner_input.setAdapter(arrayAdapter);
        spinner_output.setAdapter(arrayAdapter);

        if(systemConverter.getSrcSystem() == NumberSystem.two)
            spinner_input.setSelection(0);
        else if(systemConverter.getSrcSystem() == NumberSystem.eight)
            spinner_input.setSelection(1);
        else if(systemConverter.getSrcSystem() == NumberSystem.ten)
            spinner_input.setSelection(2);
        else
            spinner_input.setSelection(3);
        if(systemConverter.getResSystem() == NumberSystem.two)
            spinner_output.setSelection(0);
        else if(systemConverter.getResSystem() == NumberSystem.eight)
            spinner_output.setSelection(1);
        else if(systemConverter.getResSystem() == NumberSystem.ten)
            spinner_output.setSelection(2);
        else
            spinner_output.setSelection(3);


            spinner_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                    systemConverter.setSrcSystem(NumberSystem.two);
                else if(i == 1)
                    systemConverter.setSrcSystem(NumberSystem.eight);
                else if(i == 2)
                    systemConverter.setSrcSystem(NumberSystem.ten);
                else
                    systemConverter.setSrcSystem(NumberSystem.sixteen);
                synchronize(inputValue, outputValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
        spinner_output.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                    systemConverter.setResSystem(NumberSystem.two);
                else if(i == 1)
                    systemConverter.setResSystem(NumberSystem.eight);
                else if(i == 2)
                    systemConverter.setResSystem(NumberSystem.ten);
                else
                    systemConverter.setResSystem(NumberSystem.sixteen);
                synchronize(inputValue, outputValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
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
        initialize(R.id.btn_A, inputValue, outputValue);
        initialize(R.id.btn_B, inputValue, outputValue);
        initialize(R.id.btn_C, inputValue, outputValue);
        initialize(R.id.btn_D, inputValue, outputValue);
        initialize(R.id.btn_E, inputValue, outputValue);
        initialize(R.id.btn_F, inputValue, outputValue);

        Button button = (Button) findViewById(R.id.btn_backspace);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(inputValue.getText().length() > 1)
                {
                    systemConverter.setSrc(inputValue.getText().toString().substring(0, inputValue.getText().length() - 1));
                    synchronize(inputValue, outputValue);
                }
                else if(inputValue.getText().length() == 1)
                {
                    systemConverter.setSrc("0");
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
                systemConverter.setSrc("0");
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
                systemConverter.setSrc(sb.toString());
                synchronize(inputValue, outputValue);
            }
        });
    }

    private void synchronize(TextView inputValue, TextView outputValue)
    {
        systemConverter.calculate();
        inputValue.setText(systemConverter.getSrc());
        outputValue.setText(systemConverter.getResult());
    }
}
