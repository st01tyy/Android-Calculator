package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import com.example.calculator.表达式计算.Expression;

public class Calculator extends AppCompatActivity
{

    private String str = new String("");

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
            Log.w("Calculator", "current activity");
        }
        else if(itemId == R.id.menuItem_length)
        {
            Intent intent = new Intent(Calculator.this, LengthUnitConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_volume)
        {
            Intent intent = new Intent(Calculator.this, VolumeUnitConverter.class);
            startActivity(intent);
        }
        else if(itemId == R.id.menuItem_numberSystem)
        {
            Intent intent = new Intent(Calculator.this, NumberSystemConverter.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(Calculator.this, Help.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        final TextView textView = (TextView) findViewById(R.id.textView);
        if(savedInstanceState != null)
        {
            str = savedInstanceState.getString("str");
            textView.setText(str);
        }

        final EditText edit_text = (EditText) findViewById(R.id.edit_text);
        edit_text.setInputType(InputType.TYPE_NULL);
        initializeButtons(edit_text, R.id.cos, Type.type_2);
        initializeButtons(edit_text, R.id.divide, Type.type_1);
        initializeButtons(edit_text, R.id.dot, Type.type_1);
        initializeButtons(edit_text, R.id.eight, Type.type_1);
        initializeButtons(edit_text, R.id.five, Type.type_1);
        initializeButtons(edit_text, R.id.four, Type.type_1);
        initializeButtons(edit_text, R.id.log, Type.type_2);
        initializeButtons(edit_text, R.id.minus, Type.type_1);
        initializeButtons(edit_text, R.id.mod, Type.type_1);
        initializeButtons(edit_text, R.id.nine, Type.type_1);
        initializeButtons(edit_text, R.id.one, Type.type_1);
        initializeButtons(edit_text, R.id.percentage, Type.type_1);
        initializeButtons(edit_text, R.id.pi, Type.type_1);
        initializeButtons(edit_text, R.id.plus, Type.type_1);
        initializeButtons(edit_text, R.id.pow, Type.type_1);
        initializeButtons(edit_text, R.id.seven, Type.type_1);
        initializeButtons(edit_text, R.id.sin, Type.type_2);
        initializeButtons(edit_text, R.id.six, Type.type_1);
        initializeButtons(edit_text, R.id.tan, Type.type_2);
        initializeButtons(edit_text, R.id.three, Type.type_1);
        initializeButtons(edit_text, R.id.two, Type.type_1);
        initializeButtons(edit_text, R.id.zero, Type.type_1);
        initializeButtons(edit_text, R.id.multiply, Type.type_1);
        initializeButtons(edit_text, R.id.left, Type.type_1);
        initializeButtons(edit_text, R.id.right, Type.type_1);

        Button button = (Button) findViewById(R.id.clear);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                str = "";
                edit_text.setText("");
                textView.setText(str);
            }
        });

        button = (Button) findViewById(R.id.backspace);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(edit_text.getText().length() > 0)
                    edit_text.setText(edit_text.getText().subSequence(0, edit_text.getText().length() - 1));
            }
        });

        button = (Button) findViewById(R.id.getResult);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                str = new Expression(formatting(edit_text.getText().toString())).getResult();
                textView.setText(str);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putString("str", str);
    }

    private void initializeButtons(EditText editText, int id, Type type)
    {
        MyButton myButton = findViewById(id);
        myButton.setOnClickFunction(editText, type);
    }

    private String formatting(String str)
    {
        StringBuffer sb = new StringBuffer(str);
        for(int a = 0; a < sb.length(); a++)
        {
            if(sb.charAt(a) == 'π')
                sb.replace(a, a + 1, Double.toString(Math.PI));
            if(sb.charAt(a) == '%')
                sb.replace(a, a + 1, "/100");
        }
        return sb.toString();
    }

}
