// Generated by view binder compiler. Do not edit!
package com.example.ar_realestate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ar_realestate.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentFilterAdvBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final Button FilterBtnApply;

  @NonNull
  public final EditText FilterEditTextAddress;

  @NonNull
  public final EditText FilterEditTextBuildAge;

  @NonNull
  public final EditText FilterEditTextBuildingFloors;

  @NonNull
  public final EditText FilterEditTextDues;

  @NonNull
  public final EditText FilterEditTextFloorLoc;

  @NonNull
  public final EditText FilterEditTextNumOfBath;

  @NonNull
  public final EditText FilterEditTextPrice;

  @NonNull
  public final EditText FilterEditTextRentalIncome;

  @NonNull
  public final EditText FilterEditTextRoomNum;

  @NonNull
  public final EditText FilterEditTextSquareMeter;

  @NonNull
  public final EditText FilterEditTextWarmType;

  @NonNull
  public final Spinner FilterSpinnerAdvStatus;

  @NonNull
  public final Spinner FilterSpinnerBuildType;

  @NonNull
  public final Spinner FilterSpinnerElgCredit;

  @NonNull
  public final Spinner FilterSpinnerFront;

  @NonNull
  public final Spinner FilterSpinnerFuelType;

  @NonNull
  public final Spinner FilterSpinnerItemStatus;

  @NonNull
  public final Spinner FilterSpinnerStateBuilding;

  @NonNull
  public final Spinner FilterSpinnerSwap;

  @NonNull
  public final Spinner FilterSpinnerUsingStatus;

  @NonNull
  public final TextView TextAddress;

  @NonNull
  public final TextView TextAdvFilter;

  @NonNull
  public final TextView TextAdvStatus;

  @NonNull
  public final TextView TextBuildAge;

  @NonNull
  public final TextView TextBuildType;

  @NonNull
  public final TextView TextBuildingFloors;

  @NonNull
  public final TextView TextDues;

  @NonNull
  public final TextView TextElgForCredit;

  @NonNull
  public final TextView TextFloorLoc;

  @NonNull
  public final TextView TextFront;

  @NonNull
  public final TextView TextFuelType;

  @NonNull
  public final TextView TextItemStatus;

  @NonNull
  public final TextView TextNumOfBath;

  @NonNull
  public final TextView TextRentalIncome;

  @NonNull
  public final TextView TextRoomNum;

  @NonNull
  public final TextView TextSquareMeter;

  @NonNull
  public final TextView TextStateBuilding;

  @NonNull
  public final TextView TextSwap;

  @NonNull
  public final TextView TextUsingStatus;

  @NonNull
  public final TextView TextWarmType;

  @NonNull
  public final TextView textPrice;

  private FragmentFilterAdvBinding(@NonNull ScrollView rootView, @NonNull Button FilterBtnApply,
      @NonNull EditText FilterEditTextAddress, @NonNull EditText FilterEditTextBuildAge,
      @NonNull EditText FilterEditTextBuildingFloors, @NonNull EditText FilterEditTextDues,
      @NonNull EditText FilterEditTextFloorLoc, @NonNull EditText FilterEditTextNumOfBath,
      @NonNull EditText FilterEditTextPrice, @NonNull EditText FilterEditTextRentalIncome,
      @NonNull EditText FilterEditTextRoomNum, @NonNull EditText FilterEditTextSquareMeter,
      @NonNull EditText FilterEditTextWarmType, @NonNull Spinner FilterSpinnerAdvStatus,
      @NonNull Spinner FilterSpinnerBuildType, @NonNull Spinner FilterSpinnerElgCredit,
      @NonNull Spinner FilterSpinnerFront, @NonNull Spinner FilterSpinnerFuelType,
      @NonNull Spinner FilterSpinnerItemStatus, @NonNull Spinner FilterSpinnerStateBuilding,
      @NonNull Spinner FilterSpinnerSwap, @NonNull Spinner FilterSpinnerUsingStatus,
      @NonNull TextView TextAddress, @NonNull TextView TextAdvFilter,
      @NonNull TextView TextAdvStatus, @NonNull TextView TextBuildAge,
      @NonNull TextView TextBuildType, @NonNull TextView TextBuildingFloors,
      @NonNull TextView TextDues, @NonNull TextView TextElgForCredit,
      @NonNull TextView TextFloorLoc, @NonNull TextView TextFront, @NonNull TextView TextFuelType,
      @NonNull TextView TextItemStatus, @NonNull TextView TextNumOfBath,
      @NonNull TextView TextRentalIncome, @NonNull TextView TextRoomNum,
      @NonNull TextView TextSquareMeter, @NonNull TextView TextStateBuilding,
      @NonNull TextView TextSwap, @NonNull TextView TextUsingStatus, @NonNull TextView TextWarmType,
      @NonNull TextView textPrice) {
    this.rootView = rootView;
    this.FilterBtnApply = FilterBtnApply;
    this.FilterEditTextAddress = FilterEditTextAddress;
    this.FilterEditTextBuildAge = FilterEditTextBuildAge;
    this.FilterEditTextBuildingFloors = FilterEditTextBuildingFloors;
    this.FilterEditTextDues = FilterEditTextDues;
    this.FilterEditTextFloorLoc = FilterEditTextFloorLoc;
    this.FilterEditTextNumOfBath = FilterEditTextNumOfBath;
    this.FilterEditTextPrice = FilterEditTextPrice;
    this.FilterEditTextRentalIncome = FilterEditTextRentalIncome;
    this.FilterEditTextRoomNum = FilterEditTextRoomNum;
    this.FilterEditTextSquareMeter = FilterEditTextSquareMeter;
    this.FilterEditTextWarmType = FilterEditTextWarmType;
    this.FilterSpinnerAdvStatus = FilterSpinnerAdvStatus;
    this.FilterSpinnerBuildType = FilterSpinnerBuildType;
    this.FilterSpinnerElgCredit = FilterSpinnerElgCredit;
    this.FilterSpinnerFront = FilterSpinnerFront;
    this.FilterSpinnerFuelType = FilterSpinnerFuelType;
    this.FilterSpinnerItemStatus = FilterSpinnerItemStatus;
    this.FilterSpinnerStateBuilding = FilterSpinnerStateBuilding;
    this.FilterSpinnerSwap = FilterSpinnerSwap;
    this.FilterSpinnerUsingStatus = FilterSpinnerUsingStatus;
    this.TextAddress = TextAddress;
    this.TextAdvFilter = TextAdvFilter;
    this.TextAdvStatus = TextAdvStatus;
    this.TextBuildAge = TextBuildAge;
    this.TextBuildType = TextBuildType;
    this.TextBuildingFloors = TextBuildingFloors;
    this.TextDues = TextDues;
    this.TextElgForCredit = TextElgForCredit;
    this.TextFloorLoc = TextFloorLoc;
    this.TextFront = TextFront;
    this.TextFuelType = TextFuelType;
    this.TextItemStatus = TextItemStatus;
    this.TextNumOfBath = TextNumOfBath;
    this.TextRentalIncome = TextRentalIncome;
    this.TextRoomNum = TextRoomNum;
    this.TextSquareMeter = TextSquareMeter;
    this.TextStateBuilding = TextStateBuilding;
    this.TextSwap = TextSwap;
    this.TextUsingStatus = TextUsingStatus;
    this.TextWarmType = TextWarmType;
    this.textPrice = textPrice;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentFilterAdvBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentFilterAdvBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_filter_adv, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentFilterAdvBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Filter_btnApply;
      Button FilterBtnApply = ViewBindings.findChildViewById(rootView, id);
      if (FilterBtnApply == null) {
        break missingId;
      }

      id = R.id.Filter_editTextAddress;
      EditText FilterEditTextAddress = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextAddress == null) {
        break missingId;
      }

      id = R.id.Filter_editTextBuildAge;
      EditText FilterEditTextBuildAge = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextBuildAge == null) {
        break missingId;
      }

      id = R.id.Filter_editTextBuildingFloors;
      EditText FilterEditTextBuildingFloors = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextBuildingFloors == null) {
        break missingId;
      }

      id = R.id.Filter_editTextDues;
      EditText FilterEditTextDues = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextDues == null) {
        break missingId;
      }

      id = R.id.Filter_editTextFloorLoc;
      EditText FilterEditTextFloorLoc = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextFloorLoc == null) {
        break missingId;
      }

      id = R.id.Filter_editTextNumOfBath;
      EditText FilterEditTextNumOfBath = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextNumOfBath == null) {
        break missingId;
      }

      id = R.id.Filter_editTextPrice;
      EditText FilterEditTextPrice = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextPrice == null) {
        break missingId;
      }

      id = R.id.Filter_editTextRentalIncome;
      EditText FilterEditTextRentalIncome = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextRentalIncome == null) {
        break missingId;
      }

      id = R.id.Filter_editTextRoomNum;
      EditText FilterEditTextRoomNum = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextRoomNum == null) {
        break missingId;
      }

      id = R.id.Filter_editTextSquareMeter;
      EditText FilterEditTextSquareMeter = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextSquareMeter == null) {
        break missingId;
      }

      id = R.id.Filter_editTextWarmType;
      EditText FilterEditTextWarmType = ViewBindings.findChildViewById(rootView, id);
      if (FilterEditTextWarmType == null) {
        break missingId;
      }

      id = R.id.Filter_spinnerAdvStatus;
      Spinner FilterSpinnerAdvStatus = ViewBindings.findChildViewById(rootView, id);
      if (FilterSpinnerAdvStatus == null) {
        break missingId;
      }

      id = R.id.Filter_spinnerBuildType;
      Spinner FilterSpinnerBuildType = ViewBindings.findChildViewById(rootView, id);
      if (FilterSpinnerBuildType == null) {
        break missingId;
      }

      id = R.id.Filter_spinnerElgCredit;
      Spinner FilterSpinnerElgCredit = ViewBindings.findChildViewById(rootView, id);
      if (FilterSpinnerElgCredit == null) {
        break missingId;
      }

      id = R.id.Filter_spinnerFront;
      Spinner FilterSpinnerFront = ViewBindings.findChildViewById(rootView, id);
      if (FilterSpinnerFront == null) {
        break missingId;
      }

      id = R.id.Filter_spinnerFuelType;
      Spinner FilterSpinnerFuelType = ViewBindings.findChildViewById(rootView, id);
      if (FilterSpinnerFuelType == null) {
        break missingId;
      }

      id = R.id.Filter_spinnerItemStatus;
      Spinner FilterSpinnerItemStatus = ViewBindings.findChildViewById(rootView, id);
      if (FilterSpinnerItemStatus == null) {
        break missingId;
      }

      id = R.id.Filter_spinnerStateBuilding;
      Spinner FilterSpinnerStateBuilding = ViewBindings.findChildViewById(rootView, id);
      if (FilterSpinnerStateBuilding == null) {
        break missingId;
      }

      id = R.id.Filter_spinnerSwap;
      Spinner FilterSpinnerSwap = ViewBindings.findChildViewById(rootView, id);
      if (FilterSpinnerSwap == null) {
        break missingId;
      }

      id = R.id.Filter_spinnerUsingStatus;
      Spinner FilterSpinnerUsingStatus = ViewBindings.findChildViewById(rootView, id);
      if (FilterSpinnerUsingStatus == null) {
        break missingId;
      }

      id = R.id.TextAddress;
      TextView TextAddress = ViewBindings.findChildViewById(rootView, id);
      if (TextAddress == null) {
        break missingId;
      }

      id = R.id.TextAdvFilter;
      TextView TextAdvFilter = ViewBindings.findChildViewById(rootView, id);
      if (TextAdvFilter == null) {
        break missingId;
      }

      id = R.id.TextAdvStatus;
      TextView TextAdvStatus = ViewBindings.findChildViewById(rootView, id);
      if (TextAdvStatus == null) {
        break missingId;
      }

      id = R.id.TextBuildAge;
      TextView TextBuildAge = ViewBindings.findChildViewById(rootView, id);
      if (TextBuildAge == null) {
        break missingId;
      }

      id = R.id.TextBuildType;
      TextView TextBuildType = ViewBindings.findChildViewById(rootView, id);
      if (TextBuildType == null) {
        break missingId;
      }

      id = R.id.TextBuildingFloors;
      TextView TextBuildingFloors = ViewBindings.findChildViewById(rootView, id);
      if (TextBuildingFloors == null) {
        break missingId;
      }

      id = R.id.TextDues;
      TextView TextDues = ViewBindings.findChildViewById(rootView, id);
      if (TextDues == null) {
        break missingId;
      }

      id = R.id.TextElgForCredit;
      TextView TextElgForCredit = ViewBindings.findChildViewById(rootView, id);
      if (TextElgForCredit == null) {
        break missingId;
      }

      id = R.id.TextFloorLoc;
      TextView TextFloorLoc = ViewBindings.findChildViewById(rootView, id);
      if (TextFloorLoc == null) {
        break missingId;
      }

      id = R.id.TextFront;
      TextView TextFront = ViewBindings.findChildViewById(rootView, id);
      if (TextFront == null) {
        break missingId;
      }

      id = R.id.TextFuelType;
      TextView TextFuelType = ViewBindings.findChildViewById(rootView, id);
      if (TextFuelType == null) {
        break missingId;
      }

      id = R.id.TextItemStatus;
      TextView TextItemStatus = ViewBindings.findChildViewById(rootView, id);
      if (TextItemStatus == null) {
        break missingId;
      }

      id = R.id.TextNumOfBath;
      TextView TextNumOfBath = ViewBindings.findChildViewById(rootView, id);
      if (TextNumOfBath == null) {
        break missingId;
      }

      id = R.id.TextRentalIncome;
      TextView TextRentalIncome = ViewBindings.findChildViewById(rootView, id);
      if (TextRentalIncome == null) {
        break missingId;
      }

      id = R.id.TextRoomNum;
      TextView TextRoomNum = ViewBindings.findChildViewById(rootView, id);
      if (TextRoomNum == null) {
        break missingId;
      }

      id = R.id.TextSquareMeter;
      TextView TextSquareMeter = ViewBindings.findChildViewById(rootView, id);
      if (TextSquareMeter == null) {
        break missingId;
      }

      id = R.id.TextStateBuilding;
      TextView TextStateBuilding = ViewBindings.findChildViewById(rootView, id);
      if (TextStateBuilding == null) {
        break missingId;
      }

      id = R.id.TextSwap;
      TextView TextSwap = ViewBindings.findChildViewById(rootView, id);
      if (TextSwap == null) {
        break missingId;
      }

      id = R.id.TextUsingStatus;
      TextView TextUsingStatus = ViewBindings.findChildViewById(rootView, id);
      if (TextUsingStatus == null) {
        break missingId;
      }

      id = R.id.TextWarmType;
      TextView TextWarmType = ViewBindings.findChildViewById(rootView, id);
      if (TextWarmType == null) {
        break missingId;
      }

      id = R.id.textPrice;
      TextView textPrice = ViewBindings.findChildViewById(rootView, id);
      if (textPrice == null) {
        break missingId;
      }

      return new FragmentFilterAdvBinding((ScrollView) rootView, FilterBtnApply,
          FilterEditTextAddress, FilterEditTextBuildAge, FilterEditTextBuildingFloors,
          FilterEditTextDues, FilterEditTextFloorLoc, FilterEditTextNumOfBath, FilterEditTextPrice,
          FilterEditTextRentalIncome, FilterEditTextRoomNum, FilterEditTextSquareMeter,
          FilterEditTextWarmType, FilterSpinnerAdvStatus, FilterSpinnerBuildType,
          FilterSpinnerElgCredit, FilterSpinnerFront, FilterSpinnerFuelType,
          FilterSpinnerItemStatus, FilterSpinnerStateBuilding, FilterSpinnerSwap,
          FilterSpinnerUsingStatus, TextAddress, TextAdvFilter, TextAdvStatus, TextBuildAge,
          TextBuildType, TextBuildingFloors, TextDues, TextElgForCredit, TextFloorLoc, TextFront,
          TextFuelType, TextItemStatus, TextNumOfBath, TextRentalIncome, TextRoomNum,
          TextSquareMeter, TextStateBuilding, TextSwap, TextUsingStatus, TextWarmType, textPrice);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}