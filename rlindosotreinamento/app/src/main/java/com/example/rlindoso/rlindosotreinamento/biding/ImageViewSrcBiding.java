package com.example.rlindoso.rlindosotreinamento.biding;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by rlindoso on 30/01/2017.
 */

public class ImageViewSrcBiding {
    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}
