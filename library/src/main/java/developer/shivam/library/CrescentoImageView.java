package developer.shivam.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

public class CrescentoImageView extends ImageView {

    Context mContext;

    Path mClipPath;
    Path mOutlinePath;

    int width = 0;
    int height = 0;

    Bitmap mBitmap;

    Paint tintColor;

    /**
     * @param curvatureHeight changes the amount of curve. Default is 50.
     */
    int curvatureHeight = 50;

    /**
     * @param tintAmount varies from 0-255
     */
    int tintAmount = 0;

    /**
     * @param tintMode whether manual or automatic. Default is TintMode.AUTOMATIC.
     */
    int tintMode = TintMode.AUTOMATIC;

    static public class TintMode {
        static int AUTOMATIC = 0;
        static int MANUAL = 1;
    }

    Paint mPaint;
    private PorterDuffXfermode porterDuffXfermode;
    private String TAG = "CRESCENTO_IMAGE_VIEW";

    public CrescentoImageView(Context context) {
        super(context);
        init(context, null);
    }

    public CrescentoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;

        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);

        mClipPath = new Path();
        mOutlinePath = new Path();

        TypedArray styledAttributes = mContext.obtainStyledAttributes(attrs, R.styleable.CrescentoImageView, 0, 0);
        if (styledAttributes.hasValue(R.styleable.CrescentoImageView_crescent)) {
            curvatureHeight = (int) styledAttributes.getDimension(R.styleable.CrescentoImageView_crescent, getDpForPixel(curvatureHeight));
        }


        styledAttributes.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        height = getMeasuredHeight();

        mClipPath = PathProvider.getClipPath(width, height, curvatureHeight,
                getPaddingTop(), getPaddingBottom(), getPaddingLeft(), getPaddingRight());

        ViewCompat.setElevation(this, ViewCompat.getElevation(this));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                setOutlineProvider(getOutlineProvider());
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }

        if (getDrawable() != null) {
            BitmapDrawable mBitmapDrawable = (BitmapDrawable) getDrawable();
            mBitmap = mBitmapDrawable.getBitmap();
            pickColorFromBitmap(mBitmap);
        } else {
            if (getBackground() != null) {
                BitmapDrawable mBitmapDrawable = (BitmapDrawable) getBackground();
                mBitmap = mBitmapDrawable.getBitmap();
                pickColorFromBitmap(mBitmap);
            }
        }
    }

    private void pickColorFromBitmap(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int defaultColor = 0x000000;
                tintColor = new Paint(Paint.ANTI_ALIAS_FLAG);
                tintColor.setColor(Color.parseColor("#" + palette.getVibrantColor(defaultColor)));
                tintColor.setAlpha(130);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public ViewOutlineProvider getOutlineProvider() {
        return new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setConvexPath(PathProvider.getOutlinePath(width, height, curvatureHeight,
                        getPaddingTop(), getPaddingBottom(), getPaddingLeft(), getPaddingRight()));
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        mPaint.setXfermode(porterDuffXfermode);
        canvas.drawPath(mClipPath, mPaint);
        canvas.drawPath(mClipPath, tintColor);
        canvas.restoreToCount(saveCount);
        mPaint.setXfermode(null);
    }

    private int getDpForPixel (int pixel) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel, mContext.getResources().getDisplayMetrics());
    }
}
