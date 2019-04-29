package com.example.firebase;

import android.view.View;

public interface INavigation {

    void goToRegister(View view);
    void goToLogin(View view);
    void goToProfiel(View view,String username);
    void gobacktoLogin(View view);
    void gotoMYprofile(View view,String username);
    void gobacktoMYprofile(View view,String username);
    void gotoMYpProf(View view);
}
