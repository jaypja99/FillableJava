
package com.example.fillablejava;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.FillableLoaderBuilder;
import com.github.jorgecastillo.clippingtransforms.WavesClippingTransform;
import com.github.jorgecastillo.listener.OnStateChangeListener;


public class FillableLoaderPage extends Fragment implements OnStateChangeListener, ResettableView {

  @Bind(R.id.fillableLoader) @Nullable FillableLoader fillableLoader;
  private View rootView;
  private int pageNum;
  private int mPercentage = 20;

  public static FillableLoaderPage newInstance(int pageNum) {
    FillableLoaderPage page = new FillableLoaderPage();
    Bundle bundle = new Bundle();
    bundle.putInt("pageNum", pageNum);
    page.setArguments(bundle);

    return page;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    pageNum = getArguments().getInt("pageNum", 0);

    switch (pageNum) {
      case 0:
        rootView = inflater.inflate(R.layout.fragment_fillable_loader_first_page, container, false);
        fillableLoader = rootView.findViewById(R.id.fillableLoader);
        break;
      case 1:
        rootView =
            inflater.inflate(R.layout.fragment_fillable_loader_second_page, container, false);
        fillableLoader = rootView.findViewById(R.id.fillableLoader);
        break;
      case 2:
        rootView = inflater.inflate(R.layout.fragment_fillable_loader_third_page, container, false);
        fillableLoader = rootView.findViewById(R.id.fillableLoader);
        break;
      case 3:
        rootView =
            inflater.inflate(R.layout.fragment_fillable_loader_fourth_page, container, false);
        fillableLoader = rootView.findViewById(R.id.fillableLoader);
        break;
      case 4:
        rootView = inflater.inflate(R.layout.fragment_fillable_loader_fifth_page, container, false);
        fillableLoader = rootView.findViewById(R.id.fillableLoader);
        break;
      case 5:
        rootView = inflater.inflate(R.layout.fragment_fillable_loader_sixth_page, container, false);
        fillableLoader = rootView.findViewById(R.id.fillableLoader);
        break;
      case 6:
        rootView =
                inflater.inflate(R.layout.fragment_fillable_loader_seventh_page, container, false);
        fillableLoader = rootView.findViewById(R.id.fillableLoader);
        break;
      case 7:
        rootView =
                inflater.inflate(R.layout.fragment_fillable_loader_eighth_page, container, false);
        fillableLoader = rootView.findViewById(R.id.fillableLoader);
        break;
      default:
        rootView =
            inflater.inflate(R.layout.fragment_fillable_loader_seventh_page, container, false);
        fillableLoader = rootView.findViewById(R.id.fillableLoader);
        break;
    }

    return rootView;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, rootView);
    setupFillableLoader(pageNum);
  }

  private void setupFillableLoader(int pageNum) {

    if (pageNum == 3) {
      int viewSize = getResources().getDimensionPixelSize(R.dimen.fab_compat_margin);
      FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(viewSize, viewSize);
      params.gravity = Gravity.CENTER;

      FillableLoaderBuilder loaderBuilder = new FillableLoaderBuilder();
      fillableLoader = loaderBuilder.parentView((FrameLayout) rootView)
          .svgPath(Paths.JOB_AND_TALENT)
          .layoutParams(params)
          .originalDimensions(970, 970)
          .strokeColor(Color.parseColor("#1c9ade"))
          .strokeDrawingDuration(2000)
          .clippingTransform(new WavesClippingTransform())
          .fillDuration(10000)
          .build();
    } else if (pageNum == 6) {
      int viewSize = getResources().getDimensionPixelSize(R.dimen.fab_compat_margin);
      FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(viewSize, viewSize);
      params.gravity = Gravity.CENTER;

      SeekBar mSeekbar = (SeekBar) rootView.findViewById(R.id.PercentageSeekBar);
      mSeekbar.setProgress(mPercentage);
      mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
      {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          mPercentage = progress;
          fillableLoader.setPercentage(progress);
        }

        public void onStartTrackingTouch(SeekBar seekBar) { }

        public void onStopTrackingTouch(SeekBar seekBar) { }
      });

      FillableLoaderBuilder loaderBuilder = new FillableLoaderBuilder();
      fillableLoader = loaderBuilder.parentView((FrameLayout) rootView)
          .svgPath(Paths.JOB_AND_TALENT)
          .layoutParams(params)
          .percentage(mPercentage)
          .originalDimensions(970, 970)
          .strokeColor(Color.parseColor("#1c9ade"))
          .fillColor(Color.parseColor("#1c9ade"))
          .strokeDrawingDuration(2000)
          .clippingTransform(new WavesClippingTransform())
          .fillDuration(10000)
          .build();
    } else {
      fillableLoader.setSvgPath(pageNum == 0 ? Paths.INDOMINUS_REX : pageNum == 1 ? Paths.RONALDO
          : pageNum == 2 ? Paths.SEGA : pageNum == 4 ? Paths.COCA_COLA : Paths.GITHUB);
    }

    fillableLoader.setOnStateChangeListener(this);
  }

  @Override public void onStateChange(int state) {
    ((MainActivity) getActivity()).showStateHint(state);
  }

  @Override public void reset() {
    fillableLoader.reset();
    fillableLoader.postDelayed(new Runnable() {
      @Override public void run() {
        fillableLoader.start();
      }
    }, 250);
  }
}
