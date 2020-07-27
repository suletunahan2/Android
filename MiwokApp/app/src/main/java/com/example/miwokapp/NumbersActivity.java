package com.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;



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



    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


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
//                mediaPlayer =MediaPlayer.create(NumbersActivity.this,word.getAudioResourceId());
//                mediaPlayer.start();
//
//                // Setup a listener on the media player, so that we can stop and release the
//                // media player once the sound has finished playing.
//                mediaPlayer.setOnCompletionListener(mCompletionListener);


                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //mediaPlayer.start();
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                    // Start the audio file
                    mediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }

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



            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


}