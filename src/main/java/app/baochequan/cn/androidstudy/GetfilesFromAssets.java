package app.baochequan.cn.androidstudy;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author gaohangbo
 * date: 2018/7/6 0006.
 */
public class GetfilesFromAssets {
    private Activity activity;
    private String[] files;

    public GetfilesFromAssets(Context context) {
        this.activity = (Activity) context;
    }
    public String[] getfileFromAssets(String path) {
        AssetManager assetManager = activity.getAssets();
        // String[] files;
        try {
            files = assetManager.list(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return files;
    }
    public List listHtmlOfAssets() {
        List list = new ArrayList();
        files = getfileFromAssets("pdf");
        for (int i = 0; i < files.length; i++) {
            HashMap map = new HashMap();
            map.put("htmlname", files[i]);
            list.add(map);
        }
        return list;
    }

}
