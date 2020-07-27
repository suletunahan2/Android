package com.example.second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitOrder(View view) {

        //String priceMessage="Total Prices $"+ price+"\nThank you!";
        CheckBox checkBox=(CheckBox) findViewById(R.id.checkbox);
        boolean ischeck=checkBox.isChecked();
        Log.v("MainActivity","Has whipped cream: "+ischeck);

        CheckBox checkBox2=(CheckBox) findViewById(R.id.chocolate);
        boolean ischeck2=checkBox2.isChecked();
        Log.v("MainActivity","Has chocolate: "+ischeck2);

        EditText editText=(EditText)findViewById(R.id.edit) ;
       // Editable name=editText.getText();//Data type is Editable
        String name2=editText.getText().toString();//Data type is String


        int price=calculatePrice(quantity,ischeck,ischeck2);
        String summary = createOrderSummmary(price,ischeck,ischeck2,name2);

        Intent intent = new Intent(Intent.ACTION_SENDTO);//allcaps constant demektir.
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name2);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(summary);
//
//        //this code is intent for map.
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6, -122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }


    }

    public int calculatePrice(int quantity,boolean addWhipper,boolean addChocolate){
        int basePrice=5;
        if(addWhipper){
            basePrice=basePrice+1;
        }
        if(addChocolate){
            basePrice=basePrice+2;
        }
        int price=basePrice*quantity;
        return price;
    }
    private String createOrderSummmary(int price,boolean ischeck,boolean ischeck2,String name){
        //String summary=getString(R.string.order_summary_name,name)+"\n "+ getString(R.string.order_summary_whipped_cream,ischeck)+ "\n"+getString(R.string.order_summary_chocolate,ischeck2)+"\n"+getString(R.string.order_summary_quantity,quantity)+"\n"+ getString(R.string.order_summary_price,NumberFormat.getCurrencyInstance().format(price))+ "\n"+getString(R.string.thank_you);
        String summary="Name: "+name+"\nAdd whipped cream? "+ischeck+ "\nAdd chocolate? "+ischeck2+"\nQuantity: "+quantity+"\nTotal:$"+price+ "\n"+getString(R.string.thank_you);
        return summary;
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView=(TextView)findViewById(R.id.order_summary_textview);
        orderSummaryTextView.setText(message);
    }


    private void display(int number) {
        TextView quantityTextView=(TextView)findViewById(R.id.textView2);
        quantityTextView.setText(""+number);
    }

    /**
     * This method displays the given quantity value on the screen.
    private void displayPrice(int number){
        TextView priceTextView=(TextView)findViewById(R.id.price);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
     */



    public void increment(View view) {
        if(quantity<100){
            quantity=quantity+1;
        }
        else {
            //Toast message
            Toast.makeText(this,"You cannot have more than 100 coffes ",Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity>1){
            quantity=quantity-1;
        }
        else {
            Toast.makeText(this,"You cannot have less than 0 coffees ",Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

}