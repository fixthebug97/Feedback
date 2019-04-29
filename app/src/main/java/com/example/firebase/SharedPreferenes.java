package com.example.firebase;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenes {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferenes(Context context){

        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getResources().getString(R.string.login_preference),Context.MODE_PRIVATE);

    }


    public void writeloginstatus(boolean status){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status),status);
        editor.commit();

    }

    public boolean Readloginstatus(){

        boolean status=false;
        status=sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status),false);

        return status;
    }

    public void WriteUserName(String username){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.login_user),username);
        editor.commit();
    }

    public String ReadUserName(){

      String  username=sharedPreferences.getString(context.getResources().getString(R.string.login_user),"");
        return username;

    }


    public void WriteUserFullName(String fullname){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.login_name),fullname);
        editor.commit();
    }

    public String ReadUserFullName(){

        String  fullname=sharedPreferences.getString(context.getResources().getString(R.string.login_name),"");
        return fullname;

    }



    public void WriteUserInfo(String info){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.login_info),info);
        editor.commit();
    }

    public String ReadUserInfo(){

        String  info=sharedPreferences.getString(context.getResources().getString(R.string.login_info),"");
        return info;

    }
    public void WriteImageUrl(String imageURL){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.login_imageurl),imageURL);
        editor.commit();
    }

    public String ReadImageUrl(){

        String  imageURL=sharedPreferences.getString(context.getResources().getString(R.string.login_imageurl),"");
        return imageURL;

    }

}
