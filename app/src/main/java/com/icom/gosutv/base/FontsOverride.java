package com.icom.gosutv.base;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by trungpt on 4/2/15.
 */
public final class FontsOverride
{
    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName)
    {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface)
    {
        try
        {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        }
        catch (NoSuchFieldException e)
        {
            Log.e(FontsOverride.class.toString(), "Error for override font NoSuchFieldException");
        }
        catch (IllegalAccessException e)
        {
            Log.e(FontsOverride.class.toString(), "Error for override font IllegalAccessException");
        }
    }
}
