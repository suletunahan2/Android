package com.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;import android.view.View;
import android.widget.AdapterView;
import android.media.MediaPlayer;


public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;


    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_colors);
        setContentView(R.layout.word_list);

        // Create a list of words (ses dosyası eklerken final getirildi)
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "kırmızı",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("mustard yellow", "hardal sarısı",R.drawable.color_mustard_yellow,R.raw.color_mustard));
        words.add(new Word("dusty yellow", "fosforlu sarı",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("green", "yeşil",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("brown", "kahverengi",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray", "gri",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black", "siyah",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white", "beyaz",R.drawable.color_white,R.raw.color_white));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_colors);

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
                releaseMediaPlayer();
                Word word=words.get(position);
                mediaPlayer =MediaPlayer.create(ColorsActivity.this,word.getAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mCompletionListener);
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