package org.tasks.filters;

import android.os.Parcel;
import android.os.Parcelable;
import com.todoroo.andlib.sql.Criterion;
import com.todoroo.andlib.sql.QueryTemplate;
import com.todoroo.astrid.api.Filter;
import com.todoroo.astrid.dao.TaskDao.TaskCriteria;
import com.todoroo.astrid.data.Task;
import org.tasks.time.DateTime;

public class RecentlyModifiedFilter extends Filter {

  public static final Parcelable.Creator<RecentlyModifiedFilter> CREATOR =
      new Parcelable.Creator<RecentlyModifiedFilter>() {

        /** {@inheritDoc} */
        @Override
        public RecentlyModifiedFilter createFromParcel(Parcel source) {
          RecentlyModifiedFilter item = new RecentlyModifiedFilter();
          item.readFromParcel(source);
          return item;
        }

        /** {@inheritDoc} */
        @Override
        public RecentlyModifiedFilter[] newArray(int size) {
          return new RecentlyModifiedFilter[size];
        }
      };

  public RecentlyModifiedFilter(String listingTitle) {
    super(listingTitle, getQueryTemplate());
  }

  private RecentlyModifiedFilter() {}

  private static QueryTemplate getQueryTemplate() {
    return new QueryTemplate()
        .where(
            Criterion.and(
                TaskCriteria.notDeleted(),
                Task.MODIFICATION_DATE.gt(
                    new DateTime().minusDays(1).startOfMinute().getMillis())));
  }

  @Override
  public boolean supportsHiddenTasks() {
    return false;
  }
}
