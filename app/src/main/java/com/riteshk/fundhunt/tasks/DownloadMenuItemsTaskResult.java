package com.riteshk.fundhunt.tasks;

import org.json.JSONObject;

public interface DownloadMenuItemsTaskResult {
    void onMenuItemsDownload(JSONObject object);
}
