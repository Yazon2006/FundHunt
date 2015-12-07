package com.riteshk.fundhunt.tasks;

import org.json.JSONObject;

public interface DownloadWebpageTaskResult {
    void onResult(JSONObject object);
}
