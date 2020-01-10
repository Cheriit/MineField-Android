package com.cherit.minefield;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cherit.minefield.ui.game.components.Dialog;
import com.cherit.minefield.ui.game.components.Notch;

public class MainActivity extends AppCompatActivity implements Dialog.OnGameEndedListener, Notch.OnGameRetry {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void endGame() {
        getSupportFragmentManager().popBackStack();
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_home);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void retryGame() {
        getSupportFragmentManager().popBackStack();
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_game);
    }
}
