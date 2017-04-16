package com.usc.searchonfb.utils;

import android.content.SharedPreferences;

import com.usc.searchonfb.FacebookApplication;
import com.usc.searchonfb.rest.model.SearchModel.SearchData;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;

import static com.usc.searchonfb.utils.Constants.CONST_ID;
import static com.usc.searchonfb.utils.Constants.CONST_NAME;
import static com.usc.searchonfb.utils.Constants.CONST_PICTURE_URL;
import static com.usc.searchonfb.utils.Constants.CONST_TYPE;

/**
 * Created by adarsh on 4/16/2017.
 */

public class FavoriteSharedPreference {

    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private static final String PREFERENCE_FILE_NAME = "favorite";


    public static boolean addFavoriteItem(FacebookApplication app, SearchData mData, String mType) {
        SharedPreferences mSharedPreferneces = getSharedPreference(app);
        SharedPreferences.Editor mEditor = mSharedPreferneces.edit();
        JSONObject favoriteItem = new JSONObject();

        String name = "";
        String id = "";
        String pictureUrl = "";

        if (mData != null) {
            if (mData.getId() != null) {
                id = mData.getId();
            }

            if (mData.getName() != null) {
                name = mData.getName();
            }

            if (mData.getPicture() != null && mData.getPicture().getData() != null && mData.getPicture().getData().getUrl() != null) {
                pictureUrl = mData.getPicture().getData().getUrl();
            }
        }

        try {
            favoriteItem.put(CONST_NAME, name);
            favoriteItem.put(CONST_ID, id);
            favoriteItem.put(CONST_PICTURE_URL, pictureUrl);
            favoriteItem.put(CONST_TYPE, mType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void getAllFavorites(){
        String strJson = sharedPref.getString("jsondata","0");//second parameter is necessary ie.,Value to return if this preference does not exist.
        if(strJson != null) JSONObject jsonData = new JSONObject(strJson);
    }

    public static boolean deleteFavoriteItem(FacebookApplication app, SearchData mData) {
        return true;
    }

    public static boolean isFavorite(FacebookApplication app, SearchData mData) {
        SharedPreferences mSharedPreferneces = getSharedPreference(app);
        Map<String, ?> allEntries = mSharedPreferneces.getAll();
        if (allEntries.containsKey(mData.getId())) {
            return true;
        }
        return false;
    }

    public static boolean toggleFavourite(FacebookApplication app, SearchData mData) {
        //just added to favorite return true

        //just deleted from favorite return false

        return false;
    }

    private static synchronized SharedPreferences getSharedPreference(FacebookApplication app) {
        SharedPreferences mSharedPreferneces = app.getApplicationContext().getSharedPreferences(PREFERENCE_FILE_NAME, PREFERENCE_MODE_PRIVATE);
        return mSharedPreferneces;
    }
}
