package com.example.fillablejava;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.fillablejava.adapter.FillablePagesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author jorge
 * @since 7/08/15
 */
public class MainActivity extends AppCompatActivity {

  ViewPager pager;
  TextView clippingModeText;
  FloatingActionButton fab ;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    fab=  findViewById(R.id.fab);
    clippingModeText = findViewById(R.id.clippingTransformMode);
    ButterKnife.bind(this);
    setupPagination();
    setupDisableTraceButton();
  }

  private void setupPagination() {
    pager =  findViewById(R.id.pager);
    final FillablePagesAdapter adapter = new FillablePagesAdapter(getSupportFragmentManager());
    pager.setAdapter(adapter);
    pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override public void onPageSelected(int position) {
        super.onPageSelected(position);
        ((ResettableView) adapter.getItem(position)).reset();
        clippingModeText.setText(adapter.getPageTitle(position));
      }
    });

//    pager.post(new Runnable() {
//      @Override public void run() {
//        ((ResettableView) adapter.getItem(0)).reset();
//        clippingModeText.setText(adapter.getPageTitle(0));
//      }
//    });
  }

  private void setupDisableTraceButton() {
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

      }
    });
  }

  public void showStateHint(int state) {
    try {
      Snackbar.make(fab, "State changed callback: " + state, Snackbar.LENGTH_SHORT).show();
    } catch (NullPointerException e) {
      Log.d("showStateHint", e.toString());
    }
  }
}