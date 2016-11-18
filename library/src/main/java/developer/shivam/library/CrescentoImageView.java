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

    Paint tintPaint;

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

    /**
     * @param tintPaint color of tint to be applied
     */
    int tintColor = 0;

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
        if (styledAttributes.hasValue(R.styleable.CrescentoImageView_curvature)) {
            curvatureHeight = (int) styledAttributes.getDimension(R.styleable.CrescentoImageView_curvature, getDpForPixel(curvatureHeight));
        }

        if (styledAttributes.hasValue(R.styleable.CrescentoImageView_tintAlpha)) {
            if (styledAttributes.getInt(R.styleable.CrescentoImageView_tintAlpha, 0) <= 255
                    && styledAttributes.getInt(R.styleable.CrescentoImageView_tintAlpha, 0) >= 0) {
                tintAmount = styledAttributes.getInt(R.styleable.CrescentoImageView_tintAlpha, 0);
            }
        }

        if (styledAttributes.hasValue(R.styleable.CrescentoImageView_tintMode)) {
            if (styledAttributes.getInt(R.styleable.CrescentoImageView_tintMode, 0) == TintMode.AUTOMATIC) {
                tintMode = TintMode.AUTOMATIC;
            } else {
                tintMode = TintMode.MANUAL;
            }
        }

        if (styledAttributes.hasValue(R.styleable.CrescentoImageView_tintColor)) {
            tintColor = styledAttributes.getColor(R.styleable.CrescentoImageView_tintColor, 0);
        }

        styledAttributes.recycle();

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
    }

    private void pickColorFromBitmap(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                if (tintMode == TintMode.AUTOMATIC) {
                    int defaultColor = 0x000000;
                    tintPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    if (palette.getDarkVibrantColor(defaultColor) != 0) {
                        tintPaint.setColor(Color.parseColor("#" + Math.abs(palette.getDarkVibrantColor(defaultColor))));
                    } else if (palette.getDarkMutedColor(defaultColor) != 0) {
                        System.out.println(palette.getMutedColor(defaultColor));
                        tintPaint.setColor(Color.parseColor("#" + Math.abs(palette.getDarkMutedColor(defaultColor))));
                    } else {
                        tintPaint.setColor(Color.WHITE);
                    }
                    tintPaint.setAlpha(tintAmount);
                } else {
                    tintPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    tintPaint.setColor(tintColor);
                    tintPaint.setAlpha(tintAmount);
                }
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
        if (tintPaint != null) {
            canvas.drawColor(tintPaint.getColor());
        }
        canvas.drawPath(mClipPath, mPaint);
        canvas.restoreToCount(saveCount);
        mPaint.setXfermode(null);
    }

    private int getDpForPixel (int pixel) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel, mContext.getResources().getDisplayMetrics());
    }

    /**
     * Getter for attributes
     */

    public void setCurvature(int height) {
        curvatureHeight = getDpForPixel(height);
    }

    public void setTintColor(int tintColor) {
        this.tintColor = tintColor;
    }

    public void setTintMode(int tintMode) {
        this.tintMode = tintMode;
    }

    public void setTintAmount(int tintAmount) {
        this.tintAmount = tintAmount;
    }
}
