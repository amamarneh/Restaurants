package com.am.restauarnts.ui.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;
import com.kennyc.bottomsheet.menu.BottomSheetMenu;

public abstract class BaseActivity extends AppCompatActivity {
    public void bottomSheet(Context context, String title, BottomSheetMenu sheetMenu, BottomSheetListener sheetListener) {
        new BottomSheet.Builder(context)
                .setMenu(sheetMenu)
                .setTitle(title)
                .setListener(sheetListener)
                .show();
    }
    protected void setupBackArrow(){
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
