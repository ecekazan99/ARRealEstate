// Generated by view binder compiler. Do not edit!
package com.example.ar_realestate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ar_realestate.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AdvItemBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView advItemImageViewAdv;

  @NonNull
  public final TextView advItemTextViewAdvAcKlama;

  @NonNull
  public final TextView advItemTextViewAdvPrice;

  @NonNull
  public final TextView advItemTextViewAdvTitle;

  private AdvItemBinding(@NonNull LinearLayout rootView, @NonNull ImageView advItemImageViewAdv,
      @NonNull TextView advItemTextViewAdvAcKlama, @NonNull TextView advItemTextViewAdvPrice,
      @NonNull TextView advItemTextViewAdvTitle) {
    this.rootView = rootView;
    this.advItemImageViewAdv = advItemImageViewAdv;
    this.advItemTextViewAdvAcKlama = advItemTextViewAdvAcKlama;
    this.advItemTextViewAdvPrice = advItemTextViewAdvPrice;
    this.advItemTextViewAdvTitle = advItemTextViewAdvTitle;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AdvItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AdvItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.adv_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AdvItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.advItem_imageViewAdv;
      ImageView advItemImageViewAdv = ViewBindings.findChildViewById(rootView, id);
      if (advItemImageViewAdv == null) {
        break missingId;
      }

      id = R.id.adv_item_textViewAdvAcıklama;
      TextView advItemTextViewAdvAcKlama = ViewBindings.findChildViewById(rootView, id);
      if (advItemTextViewAdvAcKlama == null) {
        break missingId;
      }

      id = R.id.adv_item_textViewAdvPrice;
      TextView advItemTextViewAdvPrice = ViewBindings.findChildViewById(rootView, id);
      if (advItemTextViewAdvPrice == null) {
        break missingId;
      }

      id = R.id.adv_item_textViewAdvTitle;
      TextView advItemTextViewAdvTitle = ViewBindings.findChildViewById(rootView, id);
      if (advItemTextViewAdvTitle == null) {
        break missingId;
      }

      return new AdvItemBinding((LinearLayout) rootView, advItemImageViewAdv,
          advItemTextViewAdvAcKlama, advItemTextViewAdvPrice, advItemTextViewAdvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}