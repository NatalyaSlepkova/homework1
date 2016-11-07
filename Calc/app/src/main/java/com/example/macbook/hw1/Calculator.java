////package com.example.macbook.hw1;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
////
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.IllegalFormatException;
//import java.util.StringTokenizer;
//
//public class Calculator extends AppCompatActivity {
//
//    private TextView result;
//    static String expr = "";
//    private View.OnClickListener onClickListener;
//    static boolean f = false;
//
//    private static final String KEY_OUTPUT_TEXT = "output_text";
//    CharSequence outputText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calculator);
//
//        result = (TextView)findViewById(R.id.result);
//        onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                switch (v.getId()) {
//
//                    case R.id.d0:
//                        expr += "0";
//                        break;
//                    case R.id.d1:
//                        expr += "1";
//                        break;
//                    case R.id.d2:
//                        expr += "2";
//                        break;
//                    case R.id.d3:
//                        expr += "3";
//                        break;
//                    case R.id.d4:
//                        expr += "4";
//                        break;
//                    case R.id.d5:
//                        expr += "5";
//                        break;
//                    case R.id.d6:
//                        expr += "6";
//                        break;
//                    case R.id.d7:
//                        expr += "7";
//                        break;
//                    case R.id.d8:
//                        expr += "8";
//                        break;
//                    case R.id.d9:
//                        expr += "9";
//                        break;
//                    case R.id.add:
//                        expr += " + ";
//                        break;
//                    case R.id.sub:
//                        expr += " - ";
//                        break;
//                    case R.id.mul:
//                        expr += " * ";
//                        break;
//                    case R.id.div:
//                        expr += " / ";
//                        break;
//                    case R.id.eqv:
//                        try {
//                            double ans = calculate(expr);
//                            double res = new BigDecimal(ans).setScale(5, RoundingMode.HALF_UP).doubleValue();
//                            expr = (res - (long) res == 0) ? Long.toString((long) res) : Double.toString(res);
//                        } catch (Exception e) {
//                            expr = "INVALID OPERATION";
//                            f = true;
//                        }
//                        System.out.println(expr);
//                        break;
//                    default:
//                        expr = "";
//                        break;
//                }
//                result.setText(expr);
//                if (f) {
//                    expr = "";
//                    f = false;
//                }
//            }
//        };
//
//        for (int i : new int[]{R.id.d0, R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7, R.id.d8, R.id.d9,
//                R.id.clear, R.id.mul, R.id.sub, R.id.div, R.id.eqv, R.id.add}) {
//            findViewById(i).setOnClickListener(onClickListener);
//        }
//
//
//        if (savedInstanceState != null) {
//            CharSequence x = savedInstanceState.getCharSequence(KEY_OUTPUT_TEXT);
//            System.out.println("getting saved text");
//            System.out.println(x);
//            if (x != null) {
//                System.out.println(x);
//                result.setText(x);
//            }
//        }
//
//    }
//
//    private String expression;
//    private int pos;
//
//    public double calculate(String d) {
//        pos = 0;
//        expression = d.toLowerCase();
//        try {
//            return plusMinus();
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    private void skip() {
//        while (pos < expression.length() && Character.isWhitespace(expression.charAt(pos))) {
//            pos++;
//        }
//    }
//
//    private double plusMinus() {
//        try {
//            double value = mulDiv();
//            skip();
//            while (pos < expression.length()) {
//                char op = expression.charAt(pos);
//                if (op != '+' && op != '-') {
//                    break;
//                }
//                pos++;
//                if (op == '+') {
//                    value += mulDiv();
//                } else {
//                    value -= mulDiv();
//                }
//                skip();
//            }
//            return value;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    private double mulDiv() {
//        double value = power();
//        skip();
//        while (pos < expression.length()) {
//            char op = expression.charAt(pos);
//            if (op != '*' && op != '/') {
//                break;
//            }
//            pos++;
//            if (op == '*') {
//                value *= power();
//            } else {
//                double ppp = power();
//                if (ppp == 0) {
//                    throw new NumberFormatException();
//                } else {
//                    value /= ppp;
//                }
//            }
//            skip();
//        }
//        return value;
//    }
//
//    private double power() {
//        ArrayList<Double> d = new ArrayList<>();
//        d.add(unaryOperation());
//        skip();
//        while (pos < expression.length()) {
//            char op = expression.charAt(pos);
//            if (op != '^') {
//                break;
//            }
//            pos++;
//            d.add(unaryOperation());
//            skip();
//        }
//        double value = 1;
//        for (int i = d.size() - 1; i >= 0; i--) {
//            value = Math.pow(d.get(i), value);
//        }
//        return value;
//    }
//
//    private int r;
//
//    private double unaryOperation() {
//        skip();
//
//        switch (expression.charAt(pos)) {
//            case '+':
//                pos++;
//                return power();
//            case '-':
//                pos++;
//                return -power();
//            default:
//                r = pos;
//                while (r < expression.length()) {
//                    int x = isNumberPart(expression.charAt(r));
//                    if (x == 0) {
//                        break;
//                    }
//                    r += x;
//                }
//                double res = Double.parseDouble(expression.substring(pos, r));
//                pos = r;
//                return res;
//        }
//    }
//
//    private int isNumberPart(char x) {
//        if (Character.isDigit(x) || x == '.') {
//            return 1;
//        }
//        if (x == 'e') {
//            return 2;
//        }
//        return 0;
//    }
//
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        System.out.println("saved\n");
//        outState.putCharSequence(KEY_OUTPUT_TEXT, result.getText());
//    }
//
//}


package com.example.macbook.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
                        double first = Double.parseDouble(st.nextToken());
                        String op = st.nextToken();
                        double ans = 0;
                        double second = Double.parseDouble(st.nextToken());
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
                                    if(second == 0 ){
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
                        if(ok) {
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
                if (!ok)
                {
                    expr = "";
                    ok = true;
                }
            }
        };
//        int  arr[] =new int[]{R.id.d0, R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7, R.id.d8, R.id.d9,
//                R.id.clear, R.id.mul, R.id.sub, R.id.div, R.id.eqv, R.id.add};
//        for ( int i = 0;i< 16;i++) {
//            View a = findViewById(arr[i]);
//            System.out.println(i);
//        }
        //findViewById(R.id.d1).setOnClickListener(onClickListener);
        //System.out.println("here");;
        for (int i : new int[]{R.id.d0, R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7, R.id.d8, R.id.d9,
                R.id.clear, R.id.mul, R.id.sub, R.id.div, R.id.eqv, R.id.add}) {
            //System.out.println(1);
            findViewById(i).setOnClickListener(onClickListener);
        }

        if (savedInstanceState != null) {
            CharSequence x = savedInstanceState.getCharSequence(KEY_OUTPUT_TEXT);
            System.out.println("getting saved text");
            System.out.println(x);
            if (x != null) {
                System.out.println(x);
                result.setText(x);
                //System.out.println(x + " saved ");
            }
        }
//        d1.setOnClickListener(onClickListener);
//        d2.setOnClickListener(onClickListener);
//        d3.setOnClickListener(onClickListener);
//        d4.setOnClickListener(onClickListener);
//        d5.setOnClickListener(onClickListener);
//        d6.setOnClickListener(onClickListener);
//        d7.setOnClickListener(onClickListener);
//        d8.setOnClickListener(onClickListener);
//        d9.setOnClickListener(onClickListener);
//        d0.setOnClickListener(onClickListener);
//        add.setOnClickListener(onClickListener);
//        sub.setOnClickListener(onClickListener);
//        mul.setOnClickListener(onClickListener);
//        div.setOnClickListener(onClickListener);
//        eqv.setOnClickListener(onClickListener);
//        clear.setOnClickListener(onClickListener);


//        if (savedInstanceState != null) {
//            CharSequence x = savedInstanceState.getCharSequence(KEY_OUTPUT_TEXT);
//            if (x != null) {
//                result.setText(x);
//            }
//        }

        //        for (int i : new int[]{R.id.d0, R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7, R.id.d8, R.id.d9,
//                R.id.clear, R.id.mul, R.id.sub, R.id.div, R.id.eqv, R.id.add}) {
//            findViewById(i).setOnClickListener(onClickListener);
//        }
//
//
//        if (savedInstanceState != null) {
//            CharSequence x = savedInstanceState.getCharSequence(KEY_OUTPUT_TEXT);
//            System.out.println("getting saved text");
//            System.out.println(x);
//            if (x != null) {
//                System.out.println(x);
//                result.setText(x);
//            }
//        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("saved\n");
        outState.putCharSequence(KEY_OUTPUT_TEXT, result.getText());
    }

}
