package developer.shivam.library;

import android.graphics.Path;

class PathProvider {

    static Path getOutlinePath(int width, int height, int perpendicularHeight,
                               int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {
        Path mPath = new Path();
        mPath.moveTo(paddingLeft, paddingTop);
        mPath.lineTo(paddingLeft, height - perpendicularHeight - paddingBottom);
        mPath.cubicTo(paddingLeft, height - perpendicularHeight - paddingBottom,
                width/2 - paddingRight, height - paddingBottom,
                width - paddingRight, height - perpendicularHeight - paddingBottom);
        mPath.lineTo(width - paddingRight, paddingTop);
        mPath.lineTo(width - paddingRight, paddingTop);
        mPath.lineTo(paddingLeft, paddingTop);
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
