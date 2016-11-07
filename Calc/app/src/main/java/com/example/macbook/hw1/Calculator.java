package com.example.macbook.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Calculator extends AppCompatActivity {

    private Button d1;
    private Button d2;
    private Button d3;
    private Button d4;
    private Button d5;
    private Button d6;
    private Button d7;
    private Button d8;
    private Button d9;
    private Button d0;
    private Button clear;
    private Button eqv;
    private Button add;
    private Button sub;
    private Button mul;
    private Button div;
    private TextView result;
    String expr = "";
    private View.OnClickListener onClickListener;


    private static final String KEY_OUTPUT_TEXT = "output_text";
    CharSequence outputText;
    static boolean ok = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        result = (TextView) findViewById(R.id.result);

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expr = result.getText().toString();

                if (!ok) {
                    expr = "";
                    ok = true;
                }

                switch (v.getId()) {
                    case R.id.d0:
                        expr += "0";
                        result.setText(expr);
                        break;
                    case R.id.d1:
                        expr += "1";
                        result.setText(expr);
                        break;
                    case R.id.d2:
                        expr += "2";
                        result.setText(expr);
                        break;
                    case R.id.d3:
                        expr += "3";
                        result.setText(expr);
                        break;
                    case R.id.d4:
                        expr += "4";
                        result.setText(expr);
                        break;
                    case R.id.d5:
                        expr += "5";
                        result.setText(expr);
                        break;
                    case R.id.d6:
                        expr += "6";
                        result.setText(expr);
                        break;
                    case R.id.d7:
                        expr += "7";
                        result.setText(expr);
                        break;
                    case R.id.d8:
                        expr += "8";
                        result.setText(expr);
                        break;
                    case R.id.d9:
                        expr += "9";
                        result.setText(expr);
                        break;
                    case R.id.add:
                        expr += " + ";
                        result.setText(expr);
                        break;
                    case R.id.sub:
                        expr += " - ";
                        result.setText(expr);
                        break;
                    case R.id.mul:
                        expr += " * ";
                        result.setText(expr);
                        break;
                    case R.id.div:
                        expr += " / ";
                        result.setText(expr);
                        break;
                    case R.id.eqv:
                        StringTokenizer st = new StringTokenizer(expr);
                        double first = 0;
                        try {
                            first = Double.parseDouble(st.nextToken());
                        } catch(NoSuchElementException e) {}

                        String op = st.nextToken();
                        double ans = 0;
                        double second = 0;

                        try {
                            second = Double.parseDouble(st.nextToken());
                        } catch (NoSuchElementException e) {
                        }

                        switch (op) {
                            case "+":
                                ans = first + second;
                                break;
                            case "-":
                                ans = first - second;
                                break;
                            case "*":
                                ans = first * second;
                                break;
                            case "/":
                                try {
                                    if (second == 0) {
                                        throw new Exception();
                                    } else {
                                        ans = first / second;
                                        ans = new BigDecimal(ans).setScale(5, RoundingMode.HALF_UP).doubleValue();
                                        expr = (ans - (long) ans == 0) ? Long.toString((long) ans) : Double.toString(ans);
                                    }
                                } catch (Exception e) {
                                    expr = "INVALID OPERATION";
                                    ok = false;
                                }
                                break;
                        }
                        if (ok) {
                            expr = Double.toString(ans);
                        }
                        op = "";
                        result.setText(expr);

                        break;
                    default:
                        expr = "";
                        op = "";
                        result.setText("");
                        break;
                }
            }
        };

        for (int i : new int[]{R.id.d0, R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7, R.id.d8, R.id.d9,
                R.id.clear, R.id.mul, R.id.sub, R.id.div, R.id.eqv, R.id.add}) {
            findViewById(i).setOnClickListener(onClickListener);
        }

        if (savedInstanceState != null) {
            CharSequence x = savedInstanceState.getCharSequence(KEY_OUTPUT_TEXT);
            System.out.println("getting saved text");
            System.out.println(x);
            if (x != null) {
                System.out.println(x);
                result.setText(x);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("saved\n");
        outState.putCharSequence(KEY_OUTPUT_TEXT, result.getText());
    }

}
