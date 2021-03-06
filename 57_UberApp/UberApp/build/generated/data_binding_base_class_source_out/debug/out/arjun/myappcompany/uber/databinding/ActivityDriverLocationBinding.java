// Generated by view binder compiler. Do not edit!
package arjun.myappcompany.uber.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import arjun.myappcompany.uber.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDriverLocationBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final Button callUberButton;

  @NonNull
  public final FrameLayout driverLocationLayout;

  private ActivityDriverLocationBinding(@NonNull FrameLayout rootView,
      @NonNull Button callUberButton, @NonNull FrameLayout driverLocationLayout) {
    this.rootView = rootView;
    this.callUberButton = callUberButton;
    this.driverLocationLayout = driverLocationLayout;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDriverLocationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDriverLocationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_driver_location, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDriverLocationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.callUberButton;
      Button callUberButton = ViewBindings.findChildViewById(rootView, id);
      if (callUberButton == null) {
        break missingId;
      }

      FrameLayout driverLocationLayout = (FrameLayout) rootView;

      return new ActivityDriverLocationBinding((FrameLayout) rootView, callUberButton,
          driverLocationLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
