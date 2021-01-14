package com.onionsearchengine.onionmessenger.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.onionsearchengine.onionmessenger.R;
import com.onionsearchengine.onionmessenger.utils.ThemeHelper;

import static com.onionsearchengine.onionmessenger.ui.XmppActivity.configureActionBar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(ThemeHelper.find(this));

        setContentView(R.layout.activity_about);
        setSupportActionBar(findViewById(R.id.toolbar));
        configureActionBar(getSupportActionBar());
        setTitle(getString(R.string.title_activity_about_x, getString(R.string.app_name)));
    }
}
