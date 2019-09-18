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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.转换器.LengthConverter;
import com.example.calculator.转换器.LengthUnit;

import static com.example.calculator.Converters.lengthConverter;

public class LengthUnitConverter extends AppCompatActivity
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
            Intent intent = new Intent(LengthUnitConverter.this, Calculator.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_length)
        {

        }
        else if(itemId == R.id.menuItem_volume)
        {
            Intent intent = new Intent(LengthUnitConverter.this, VolumeUnitConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_numberSystem)
        {
            Intent intent = new Intent(LengthUnitConverter.this, NumberSystemConverter.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(LengthUnitConverter.this, Help.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_converter);
        if(lengthConverter == null)
            lengthConverter = new LengthConverter();

        final TextView inputValue = (TextView)findViewById(R.id.textView_inputValue);
        final TextView outputValue = (TextView)findViewById(R.id.textView_outputValue);
        synchronize(inputValue, outputValue);

        Spinner spinner_input = (Spinner) findViewById(R.id.spinner_input);
        Spinner spinner_output = (Spinner) findViewById(R.id.spinner_output);

        LengthUnit srcUnit = lengthConverter.getSrcUnit();
        LengthUnit resUnit = lengthConverter.getResUnit();
        if(srcUnit == LengthUnit.mm)
        {
            spinner_input.setSelection(0);
        }
        else if(srcUnit == LengthUnit.cm)
        {
            spinner_input.setSelection(1);
        }
        else if(srcUnit == LengthUnit.dm)
        {
            spinner_input.setSelection(2);
        }
        else if(srcUnit == LengthUnit.m)
        {
            spinner_input.setSelection(3);
        }
        else if(srcUnit == LengthUnit.km)
        {
            spinner_input.setSelection(4);
        }
        else if(srcUnit == LengthUnit.in)
        {
            spinner_input.setSelection(5);
        }
        else
        {
            spinner_input.setSelection(6);
        }
        if(resUnit == LengthUnit.mm)
        {
            spinner_output.setSelection(0);
        }
        else if(resUnit == LengthUnit.cm)
        {
            spinner_output.setSelection(1);
        }
        else if(resUnit == LengthUnit.dm)
        {
            spinner_output.setSelection(2);
        }
        else if(resUnit == LengthUnit.m)
        {
            spinner_output.setSelection(3);
        }
        else if(resUnit == LengthUnit.km)
        {
            spinner_output.setSelection(4);
        }
        else if(resUnit == LengthUnit.in)
        {
            spinner_output.setSelection(5);
        }
        else
        {
            spinner_output.setSelection(6);
        }
        spinner_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                {
                    lengthConverter.setSrcUnit(LengthUnit.mm);
                }
                else if(i == 1)
                {
                    lengthConverter.setSrcUnit(LengthUnit.cm);
                }
                else if(i == 2)
                {
                    lengthConverter.setSrcUnit(LengthUnit.dm);
                }
                else if(i == 3)
                {
                    lengthConverter.setSrcUnit(LengthUnit.m);
                }
                else if(i == 4)
                {
                    lengthConverter.setSrcUnit(LengthUnit.km);
                }
                else if(i == 5)
                {
                    lengthConverter.setSrcUnit(LengthUnit.in);
                }
                else
                {
                    lengthConverter.setSrcUnit(LengthUnit.ft);
                }
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
                {
                    lengthConverter.setResUnit(LengthUnit.mm);
                }
                else if(i == 1)
                {
                    lengthConverter.setResUnit(LengthUnit.cm);
                }
                else if(i == 2)
                {
                    lengthConverter.setResUnit(LengthUnit.dm);
                }
                else if(i == 3)
                {
                    lengthConverter.setResUnit(LengthUnit.m);
                }
                else if(i == 4)
                {
                    lengthConverter.setResUnit(LengthUnit.km);
                }
                else if(i == 5)
                {
                    lengthConverter.setResUnit(LengthUnit.in);
                }
                else
                {
                    lengthConverter.setResUnit(LengthUnit.ft);
                }
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
        initialize(R.id.btn_dot, inputValue, outputValue);
        Button button = (Button) findViewById(R.id.btn_backspace);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(inputValue.getText().length() > 1) {
                    inputValue.setText(inputValue.getText().subSequence(0, inputValue.getText().length() - 1));
                    lengthConverter.setSrc(inputValue.getText().toString());
                    lengthConverter.calculate();
                    outputValue.setText(lengthConverter.getResult());
                }
                else if(inputValue.getText().length() == 1)
                {
                    inputValue.setText("0");
                    outputValue.setText("0");
                }
            }
        });
        button = (Button) findViewById(R.id.btn_clear);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                inputValue.setText("0");
                outputValue.setText("0");
            }
        });

    }
    private void initialize(int id, final TextView inputValue, final TextView outputValue)
    {
        final Button button = (Button) findViewById(id);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                StringBuffer sb = new StringBuffer(inputValue.getText());
                if(sb.toString().equals("0"))
                    sb = new StringBuffer("");
                sb.append(button.getText());
                Log.w("LengthUnitConverter", sb.toString());
                System.out.println(sb.toString());
                lengthConverter.setSrc(sb.toString());
                synchronize(inputValue, outputValue);

            }
        });

    }

    private void synchronize(TextView inputValue, TextView outputValue)
    {
        lengthConverter.calculate();
        inputValue.setText(lengthConverter.getSrc());
        outputValue.setText(lengthConverter.getResult());
    }


}
