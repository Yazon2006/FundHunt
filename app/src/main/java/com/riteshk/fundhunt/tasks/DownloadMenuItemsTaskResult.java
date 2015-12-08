package com.riteshk.fundhunt.tasks;

import org.json.JSONObject;

/**
 * Created by Yazon on 08.12.2015.
 */
public interface DownloadMenuItemsTaskResult {
    void onMenuItemsDownload(JSONObject object);
}
