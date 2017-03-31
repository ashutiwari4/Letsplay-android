package ashutosh.letsplay.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import ashutosh.letsplay.R;


public class CTextView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "TextView";
    private Typeface mTypeface;

    public CTextView(Context context) {
        super(context);
    }

    public CTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray fontTypedArray = ctx.obtainStyledAttributes(attrs, R.styleable.custom_font_styles);
        String textStyle = fontTypedArray.getString(R.styleable.custom_font_styles_font_name_with_asset_path);
        try {
            if (textStyle == null) {
                mTypeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/ROBOTO-REGULAR.TTF");
            } else {
                mTypeface = Typeface.createFromAsset(ctx.getAssets(), textStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Could not get typeface: " + textStyle + "&&" + e.getMessage() + " " + this.getId());
        }
        setTypeface(mTypeface, fontTypedArray.getInt(R.styleable.custom_font_styles_android_textStyle, 0));
        fontTypedArray.recycle();
    }
}
