package org.iblitzc0de.movielist;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;

import org.iblitzc0de.popularlist.R;

public class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        this.mToolbar = (Toolbar) findViewById(R.id.recycler_view);
        if (this.mToolbar != null) {
            setSupportActionBar(this.mToolbar);
        }
    }
}
