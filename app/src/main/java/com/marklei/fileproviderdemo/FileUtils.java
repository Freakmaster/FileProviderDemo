package com.marklei.fileproviderdemo;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by leihao on 2017/11/21.
 */

public class FileUtils {

    /**
     * 从 Uri 获取图片本地路径
     *
     * @param context   上下文
     * @param contentUri    图片Uri
     * @return  图片的绝对路径
     */
    public static String getAbsoluteImagePath(Context context, Uri contentUri) {
        System.out.println(contentUri);
        String[] projection = {MediaStore.Images.Media.DATA};
        String urlpath;
        // 如果是 content:// 路径
        CursorLoader loader = new CursorLoader(context, contentUri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        try {
            int colunmu_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            urlpath = cursor.getString(colunmu_index);
            return urlpath;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        // 如果是 file:// 路径
        urlpath = contentUri.getPath();
        return urlpath;
    }
}
