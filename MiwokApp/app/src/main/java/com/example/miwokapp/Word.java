package com.example.miwokapp;

public class Word {
    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mtrTranslation;

    // Drawable resource ID
    private int mImageResourceId =NO_IMAGE_PROVIDED; //default NO_IMAGE_PROVIDED

    private static final int NO_IMAGE_PROVIDED = -1;

    private int mAudioResourceId;



    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param trTranslation is the word in the Turkish language
     *  @param imageResourceId is the image to represent words
     * @param audioResourceId is the English audio
     */
    public Word(String defaultTranslation, String trTranslation,int imageResourceId,int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mtrTranslation = trTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }
    public Word(String defaultTranslation, String miwokTranslation,int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mtrTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mtrTranslation;
    }

    /**
     * Get the image resource ID
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    /**
     * Returns whether or not there is an image for this word
     */
    public boolean hasImage(){
        return mImageResourceId!= NO_IMAGE_PROVIDED;//true or false
    }

}
