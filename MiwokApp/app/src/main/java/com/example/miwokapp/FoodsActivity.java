package com.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);


        setContentView(R.layout.word_list);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Donut",  "Tatlı çörek", R.drawable.donut,R.raw.donut));
        words.add(new Word("Eclair", "Ekler", R.drawable.eclair,R.raw.ekler));
        words.add(new Word("Froyo", "Froyo", R.drawable.froyo,R.raw.froyo));
        words.add(new Word("GingerBread",  "Zencefilli çörek", R.drawable.gingerbread,R.raw.ginger));
        words.add(new Word("Honeycomb",  "Bal peteği", R.drawable.honeycomb,R.raw.honey));
        words.add(new Word("Ice Cream Sandwich",  "Dondurmalı Sandviç", R.drawable.icecream,R.raw.ice));
        words.add(new Word("Jelly Bean", "Jelibon", R.drawable.jellybean,R.raw.jelly));
        words.add(new Word("KitKat", "Kit Kat", R.drawable.kitkat,R.raw.kitkat));
        words.add(new Word("Lollipop",  "Lolipop", R.drawable.lollipop,R.raw.lolipop));
        words.add(new Word("Marshmallow", "Şekerleme", R.drawable.marshmallow,R.raw.marsh));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words,R.color.primary_color);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);


        //for audio (mediaplayer and setOnItemClickListener method)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word=words.get(position);//burda variable a ulaşabilmek icin final olmalı
                mediaPlayer = MediaPlayer.create(FoodsActivity.this,word.getAudioResourceId());
                mediaPlayer.start();
            }
        });
    }



    @Override
    protected void onStop() { //stop audio (uygulamadan çıkınca ses dursun diye)
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}