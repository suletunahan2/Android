package com.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NumbersClickListener clickListener=new NumbersClickListener();
        //Find the view taht shows the numbers category
        TextView numbers=(TextView)findViewById(R.id.numbers);
        //Set a clickListener on that View
        //numbers.setOnClickListener(new NumbersClickListener()); //asagıdaki yolla yapılırsa yeni bir class a ihtiyac kalmaz.
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent=new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(numbersIntent);
                Toast.makeText(view.getContext(),"Open the list of numbers",Toast.LENGTH_SHORT).show();
            }
        });


        TextView colors=(TextView)findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent colorIndent=new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(colorIndent);
                Toast.makeText(view.getContext(),"Open the list of colors",Toast.LENGTH_SHORT).show();


            }
        });

        TextView family=(TextView)findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent familyIndent=new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(familyIndent);
                Toast.makeText(view.getContext(),"Open the list of family members",Toast.LENGTH_SHORT).show();

            }
        });

        TextView phrases=(TextView)findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent phrasesIndent=new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(phrasesIndent);
                Toast.makeText(view.getContext(),"Open the list of phrases",Toast.LENGTH_SHORT).show();

            }
        });


        TextView foods=(TextView)findViewById(R.id.foods);
        foods.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent foodsIndent=new Intent(MainActivity.this,FoodsActivity.class);
                startActivity(foodsIndent);
                Toast.makeText(view.getContext(),"Open the list of foods",Toast.LENGTH_SHORT).show();

            }
        });



    }

}