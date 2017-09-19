package com.timbuchalka.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;
    private static final String TAG = "MainActivity";

    private Double operand1 = null;
    private String pendingOperation = "=";

    public static final String STATE_PENDING_OPERATION = "PendingOperation";
    public static final String STATE_OPERAND1 = "Operand1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNunber);
        displayOperation = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);
        Button buttonNeg = (Button) findViewById(R.id.buttonNeg);
        Button buttonClear = (Button) findViewById(R.id.buttonClear);

        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                System.out.println(b.getText());
                newNumber.append(b.getText().toString());
                String value = newNumber.getText().toString();
                if(b.getText().toString().equalsIgnoreCase("Neg")) {
                    if(value.length() == 0) {
                        newNumber.setText("-");
                    } else {
                        try {
                            Double doubleValue = Double.valueOf(value);
                            doubleValue *= -1;
                            newNumber.setText(doubleValue.toString());
                        } catch (NumberFormatException e) {
                            newNumber.setText("");
                        }
                    }
                }
                if(b.getText().toString().equalsIgnoreCase("CE")){
                    newNumber.setText("");
                    result.setText("");
                    pendingOperation = "=";
                }
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);



        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                System.out.println(value + " " +  op);
                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonAdd.setOnClickListener(opListener);
        buttonNeg.setOnClickListener(listener);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                pendingOperation = "=";
                displayOperation.setText(pendingOperation);
                newNumber.setText("");
                result.setText("");
                operand1 = null;

            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);//this is where state is saved
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        displayOperation.setText(pendingOperation);
    }

    private void performOperation(Double value, String operation) {
        if (null == operand1) {
            System.out.println("value: " + value + " operation: " + operation);
            operand1 = value;
            System.out.println("if operand1 is null: " + operand1);
        } else {
            System.out.println("if operand1 is not null, operand2: " + value);
            System.out.println("and operand1: " + operand1);
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }

            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (operand1 == 0) {
                        operand1 = 0.0;
                    }
                    else {
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;

            }
        }

        result.setText(operand1.toString());
        newNumber.setText("");
    }
}
