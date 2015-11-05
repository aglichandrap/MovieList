package org.iblitzc0de.popularlist;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.mToolbar != null) {
            setSupportActionBar(this.mToolbar);
        }
    }
}
