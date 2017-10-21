package com.example.ja.magicsquare;

import android.graphics.RadialGradient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static java.util.Collections.swap;

public class Game extends AppCompatActivity {

    TextView mText;
    EditText mEdit;
    Button mButton;
    Random rnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    private void ShuffleArray(int[] array)
    {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }


    public void initialize(View v){
        setContentView(R.layout.activity_game);
        int[] tab = new int[9];
        tab[0] = 1;
        tab[1] = 2;
        tab[2] = 3;
        tab[3] = 4;
        tab[4] = 5;
        tab[5] = 6;
        tab[6] = 7;
        tab[7] = 8;
        tab[8] = 9;
        ShuffleArray(tab);
        System.out.println(tab[0]);
        System.out.println(tab[1]);
        System.out.println(tab[2]);
        System.out.println(tab[3]);
        System.out.println(tab[4]);
        System.out.println(tab[5]);
        System.out.println(tab[6]);
        System.out.println(tab[7]);
        System.out.println(tab[8]);

        int summ06 = tab[0] + tab[1] + tab[2];
        int summ26 = tab[3] + tab[4] + tab[5];
        int summ46 = tab[6] + tab[7] + tab[8];
        int summ60 = tab[0] + tab[3] + tab[6];
        int summ62 = tab[1] + tab[4] + tab[7];
        int summ64 = tab[2] + tab[5] + tab[8];

        mEdit = (EditText)findViewById(R.id.sum06);
        mEdit.setText(String.valueOf(summ06));
        mEdit = (EditText)findViewById(R.id.sum26);
        mEdit.setText(String.valueOf(summ26));
        mEdit = (EditText)findViewById(R.id.sum46);
        mEdit.setText(String.valueOf(summ46));
        mEdit = (EditText)findViewById(R.id.sum60);
        mEdit.setText(String.valueOf(summ60));
        mEdit = (EditText)findViewById(R.id.sum62);
        mEdit.setText(String.valueOf(summ62));
        mEdit = (EditText)findViewById(R.id.sum64);
        mEdit.setText(String.valueOf(summ64));

        mButton = (Button) findViewById(R.id.NewGame);
        mButton.setEnabled(false);
        mButton = (Button) findViewById(R.id.conti);
        mButton.setEnabled(false);
        mButton = (Button) findViewById(R.id.submit);
        mButton.setEnabled(true);


    }

    public void exit(View v) {
        finish();
    }

    public int convert(int n){
        int number = 0;
        mEdit = (EditText)findViewById(n);
        try {
            number = Integer.parseInt(mEdit.getText().toString());
        }
        catch (NumberFormatException nfe){
            System.out.println("Could not parse " + nfe);
        }
        return number;
    }

    public boolean areValuesUnique( int[] values )
    {
        for( int i = 0; i < values.length; ++i )
        {
            for( int j = i + 1; j < values.length; ++j )
            {
                if( values[i] == values[j] )
                    return false;
            }
        }
        return true;
    }

    public void resume(View v){
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.setEnabled(false);
        mButton = (Button) findViewById(R.id.conti);
        mButton.setEnabled(false);
        mButton = (Button) findViewById(R.id.submit);
        mButton.setEnabled(true);
    }

    public void submit(View v) {
        int[] tab = new int[9];
        tab[0] = convert(R.id.number00);
        tab[1] = convert(R.id.number02);
        tab[2] = convert(R.id.number04);

        tab[3] = convert(R.id.number20);
        tab[4] = convert(R.id.number22);
        tab[5] = convert(R.id.number24);

        tab[6] = convert(R.id.number40);
        tab[7] = convert(R.id.number42);
        tab[8] = convert(R.id.number44);

        int[] tab2 = new int[6];
        tab2[0] = convert(R.id.sum06);
        tab2[1] = convert(R.id.sum26);
        tab2[2] = convert(R.id.sum46);

        tab2[3] = convert(R.id.sum60);
        tab2[4] = convert(R.id.sum62);
        tab2[5] = convert(R.id.sum64);

        int value1 = tab[0] + tab[1] + tab[2];
        int value2 = tab[3] + tab[4] + tab[5];
        int value3 = tab[6] + tab[7] + tab[8];

        int value4 = tab[0] + tab[3] + tab[6];
        int value5 = tab[1] + tab[4] + tab[7];
        int value6 = tab[2] + tab[5] + tab[8];

        if (areValuesUnique(tab) == true) {
            if (tab2[0] != value1 || tab2[1] != value2 || tab2[2] != value3 || tab2[3] != value4 || tab2[4] != value5 || tab2[5] != value6) {
                mText = (TextView) findViewById(R.id.textView);
                mText.setText("WRONG ANSWER");
                mButton = (Button) findViewById(R.id.submit);
                mButton.setEnabled(false);
                mButton = (Button) findViewById(R.id.NewGame);
                mButton.setEnabled(true);
                mButton = (Button) findViewById(R.id.conti);
                mButton.setEnabled(true);


            } else {
                mText = (TextView) findViewById(R.id.textView);
                mText.setText("SUCCESS");
                mButton = (Button) findViewById(R.id.NewGame);
                mButton.setEnabled(true);
                mButton = (Button) findViewById(R.id.conti);
                mButton.setEnabled(false);
                mButton = (Button) findViewById(R.id.submit);
                mButton.setEnabled(false);
            }
        }
        else{
            mText = (TextView) findViewById(R.id.textView);
            mText.setText("WRONG ANSWER");
            mButton = (Button) findViewById(R.id.submit);
            mButton.setEnabled(false);
            mButton = (Button) findViewById(R.id.NewGame);
            mButton.setEnabled(true);
            mButton = (Button) findViewById(R.id.conti);
            mButton.setEnabled(true);
        }

    }


}


