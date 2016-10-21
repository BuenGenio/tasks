package com.todoroo.astrid.subtasks;

import android.text.TextUtils;

import com.todoroo.astrid.dao.TaskDao;
import com.todoroo.astrid.dao.TaskListMetadataDao;
import com.todoroo.astrid.data.SyncFlags;
import com.todoroo.astrid.data.TaskListMetadata;

import javax.inject.Inject;

public class SubtasksFilterUpdater extends SubtasksUpdater {

    private final TaskListMetadataDao taskListMetadataDao;

    @Inject
    public SubtasksFilterUpdater(TaskListMetadataDao taskListMetadataDao, TaskDao taskDao) {
        super(taskDao);

        this.taskListMetadataDao = taskListMetadataDao;
    }

    @Override
    protected String getSerializedTree(TaskListMetadata list) {
        if (list == null) {
            return "[]"; //$NON-NLS-1$
        }
        String order = list.getTaskIDs();
        if (TextUtils.isEmpty(order) || "null".equals(order)) //$NON-NLS-1$
        {
            order = "[]"; //$NON-NLS-1$
        }

        return order;
    }

    @Override
    protected void writeSerialization(TaskListMetadata list, String serialized, boolean shouldQueueSync) {
        if (list != null) {
            list.setTaskIDs(serialized);
            if (!shouldQueueSync) {
                list.putTransitory(SyncFlags.ACTFM_SUPPRESS_OUTSTANDING_ENTRIES, true);
            }
            taskListMetadataDao.saveExisting(list);
        }
    }
}
