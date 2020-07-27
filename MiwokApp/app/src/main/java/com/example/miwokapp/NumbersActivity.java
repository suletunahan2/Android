package com.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
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
        setContentView(R.layout.word_list);

//        //array
//        String[] words = new String[10];
//        words[0] = "one";
//        words[1] = "two";
//        words[2] = "three";
//        words[3] = "four";
//        words[4] = "five";
//        words[5] = "six";
//        words[6] = "seven";
//        words[7] = "eight";
//        words[8] = "nine";
//        words[9] = "ten";
//
//        Log.v("NumbersActivity","Word at index 0:"+words[0]);
//        Log.v("NumbersActivity","Word at index 1:"+words[1]);


        // Create a arraylist of words(generic class)
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one","bir",R.drawable.number_one,R.raw.one));
        words.add(new Word("two","iki",R.drawable.number_two,R.raw.two));
        words.add(new Word("three","üç",R.drawable.number_three,R.raw.three));
        words.add(new Word("four","dört",R.drawable.number_four,R.raw.four));
        words.add(new Word("five","beş",R.drawable.number_five,R.raw.five));
        words.add(new Word("six","altı",R.drawable.number_six,R.raw.six));
        words.add(new Word("seven","yedi",R.drawable.number_seven,R.raw.seven));
        words.add(new Word("eight","sekiz",R.drawable.number_eight,R.raw.eight));
        words.add(new Word("nine","dokuz",R.drawable.number_nine,R.raw.nine));
        words.add(new Word("ten","on",R.drawable.number_ten,R.raw.ten));





        // Verify the contents of the list by printing out each element to the logs
//        Log.v("NumbersActivity", "Word at index 0: " + words.get(0));
//        Log.v("NumbersActivity", "Word at index 1: " + words.get(1));

//        LinearLayout rootview=(LinearLayout)findViewById(R.id.rootview);
//        for(int i=0;i<words.size();i++){
//            TextView wordview=new TextView(this);
//            wordview.setText(words.get(i));
//            rootview.addView(wordview);
//        }


        //(Context,resource,list object)
        WordAdapter itemsAdapter=new WordAdapter(this,words,R.color.category_colors);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);




        //for audio (mediaplayer and setOnItemClickListener method)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                Word word =words.get(position);
                mediaPlayer =MediaPlayer.create(NumbersActivity.this,word.getAudioResourceId());
                mediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
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