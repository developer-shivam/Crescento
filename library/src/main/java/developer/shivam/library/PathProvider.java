package developer.shivam.library;

import android.graphics.Path;
import android.os.Build;
import android.view.View;

class PathProvider {

    private static int ALPHA = 0;

    static Path getOutlinePath(View view, int width, int height, int perpendicularHeight,
                               int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ALPHA = (int) view.getElevation();
        }
        Path mPath = new Path();
        mPath.moveTo(paddingLeft - ALPHA/4, paddingTop - ALPHA/8);
        mPath.lineTo(paddingLeft - ALPHA/4, height - perpendicularHeight - paddingBottom + ALPHA/8);
        mPath.cubicTo(paddingLeft - ALPHA/4, height - perpendicularHeight - paddingBottom + ALPHA/8,
                width/2 - paddingRight - ALPHA/4, height - paddingBottom + ALPHA/2,
                width - paddingRight + ALPHA/4, height - perpendicularHeight - paddingBottom + ALPHA/8);
        mPath.lineTo(width - paddingRight + ALPHA/4, paddingTop - ALPHA/8);
        mPath.lineTo(width - paddingRight - ALPHA/4, paddingTop - ALPHA/8);
        mPath.lineTo(paddingLeft - ALPHA/4, paddingTop - ALPHA/8);
        mPath.close();
        return mPath;
    }

    static Path getClipPath(int width, int height, int perpendicularHeight,
                            int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {
        Path mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(paddingLeft, paddingTop);
        mPath.lineTo(paddingLeft, height - perpendicularHeight - paddingBottom);
        mPath.cubicTo(paddingLeft, height - perpendicularHeight - paddingBottom,
                width/2 - paddingRight, height - paddingBottom,
                width - paddingRight, height - perpendicularHeight - paddingBottom);
        mPath.lineTo(width - paddingRight, paddingTop);
        mPath.lineTo(paddingLeft, paddingTop);
        mPath.lineTo(0, 0);
        mPath.lineTo(width, 0);
        mPath.lineTo(width, height);
        mPath.lineTo(0, height);
        mPath.close();
        return mPath;
    }
}
