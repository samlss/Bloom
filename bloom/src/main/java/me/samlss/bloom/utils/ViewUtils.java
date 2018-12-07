package me.samlss.bloom.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description utils of view.
 */
public class ViewUtils {
    private ViewUtils(){
        throw new UnsupportedOperationException("Can not be instantiated.");
    }

    /**
     * Return whether the status bar is visible.
     *
     * @param activity The activity.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isStatusBarVisible(Activity activity) {
        int flags = activity.getWindow().getAttributes().flags;
        return (flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == 0;
    }


    /**
     * Get the status bar height.
     * */
    public static int getStatusBarHeight() {
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * Get the rect on screen for view
     *
     * @param view The view
     * */
    public static RectF getRectOnScreen(View view)
    {
        if (view == null){
            Log.e("ViewUtils", "Please pass non-null referParent and child.");
            return new RectF();
        }

        RectF result = new RectF();

        int[] pos = new int[2];
        view.getLocationOnScreen(pos);

        result.left   = pos[0];
        result.top    = pos[1];
        result.right  = result.left + view.getMeasuredWidth();
        result.bottom = result.top  + view.getMeasuredHeight();

        return result;
    }

    /**
     * Create a bitmap from view.
     *
     * @param view The view you want to create the bitmap.
     * */
    public static Bitmap createBitmap(View view){
        if (view == null
                || view.getWidth() == 0
                || view.getHeight() == 0){
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    /**
     * Recycle the bitmap.
     *
     * @param bitmap The bitmap.
     * */
    public static void recycleBitmap(Bitmap bitmap){
        if (bitmap != null
                && !bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
