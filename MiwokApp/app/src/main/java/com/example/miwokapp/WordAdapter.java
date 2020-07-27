package com.example.miwokapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class WordAdapter extends ArrayAdapter<Word> {

    //resource ıd for the background color for this list of words
    private int mcolor;
    public WordAdapter(Activity context, ArrayList<Word> words,int color){
        super(context,0,words);
        mcolor=color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return list_view

        View listItemView=convertView;
        if(listItemView==null){
            //to inflate (we create a new list item layout from list_item Xml resource)
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word currentWord=getItem(position);//position icin

        TextView txtview=(TextView)listItemView.findViewById(R.id.tr_text_view);
        txtview.setText(currentWord.getMiwokTranslation());

        TextView txtview2=(TextView)listItemView.findViewById(R.id.default_text_view);
        txtview2.setText(currentWord.getDefaultTranslation());

        //final keyword:the field never change values (constant value)
        //static keyword:this variable is associated with the class and not associated with an object instance of the class.
        //public static : you can go ahead infer to this variable directly



        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
//        if (currentWord.getImageResourceId()!= 0){
//            iconView.setImageResource(currentWord.getImageResourceId());
//        } else {
//            //phrases icin
//            iconView.setVisibility(View.GONE); //invisible ile görüntünün yeri görünüyor(layout).
//        }

        //OR
        if(currentWord.hasImage()){
            iconView.setImageResource(currentWord.getImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        }
        else {
            iconView.setVisibility(View.GONE);

        }

        View textContainer=listItemView.findViewById(R.id.text_container);
        int colors= ContextCompat.getColor(getContext(),mcolor);
        textContainer.setBackgroundColor(colors);




        return listItemView;
    }
}
