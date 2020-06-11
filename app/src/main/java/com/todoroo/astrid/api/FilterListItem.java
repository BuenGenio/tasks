/*
 * Copyright (c) 2012 Todoroo Inc
 *
 * See the file "LICENSE" for the full license governing this code.
 */

package com.todoroo.astrid.api;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import java.util.Objects;
import org.tasks.R;

/**
 * Represents an item displayed by Astrid's FilterListActivity
 *
 * @author Tim Su <tim@todoroo.com>
 */
public abstract class FilterListItem implements Parcelable {

  /** Title of this item displayed on the Filters page */
  public String listingTitle = null;

  public int icon = -1;
  public int tint = 0;
  public int count = -1;
  public int order = 0;

  public abstract Type getItemType();

  @Override
  public int describeContents() {
    return 0;
  }

  /** {@inheritDoc} */
  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(listingTitle);
    dest.writeInt(icon);
    dest.writeInt(tint);
    dest.writeInt(count);
    dest.writeInt(order);
  }

  // --- parcelable helpers

  /** Utility method to read FilterListItem properties from a parcel. */
  protected void readFromParcel(Parcel source) {
    listingTitle = source.readString();
    icon = source.readInt();
    tint = source.readInt();
    count = source.readInt();
    order = source.readInt();
  }

  public abstract boolean areItemsTheSame(@NonNull FilterListItem other);

  public boolean areContentsTheSame(@NonNull FilterListItem other) {
    return Objects.equals(listingTitle, other.listingTitle)
        && icon == other.icon
        && tint == other.tint
        && count == other.count
        && order == other.order;
  }

  @Override
  public String toString() {
    return "FilterListItem{"
        + "listingTitle='"
        + listingTitle
        + '\''
        + ", icon="
        + icon
        + ", tint="
        + tint
        + ", count="
        + count
        + ", order="
        + order
        + '}';
  }

  public enum Type {
    ITEM(R.layout.filter_adapter_row),
    ACTION(R.layout.filter_adapter_action),
    SUBHEADER(R.layout.filter_adapter_subheader),
    SEPARATOR(R.layout.filter_adapter_separator);

    public final int layout;

    Type(@LayoutRes int layout) {
      this.layout = layout;
    }
  }
}
