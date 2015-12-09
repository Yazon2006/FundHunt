package com.riteshk.fundhunt.tasks;

import org.json.JSONObject;

public interface DownloadWebpageTaskResult {
    void onTableDownload(JSONObject object);
    void onErrorTableDownload();
}
