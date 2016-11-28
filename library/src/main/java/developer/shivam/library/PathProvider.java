package developer.shivam.library;

import android.graphics.Path;

class PathProvider {

    static Path getOutlinePath(int width, int height, int curvatureHeight, int direction,
                               int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {

        Path mPath = new Path();

        if (direction == CrescentoImageView.CurvatureDirection.OUTWARD) {
            mPath.moveTo(paddingLeft, paddingTop);
            mPath.lineTo(paddingLeft, height - curvatureHeight - paddingBottom);
            mPath.cubicTo(paddingLeft, height - curvatureHeight - paddingBottom,
                    width / 2 - paddingRight, height - paddingBottom,
                    width - paddingRight, height - curvatureHeight - paddingBottom);
            mPath.lineTo(width - paddingRight, paddingTop);
            mPath.lineTo(width - paddingRight, paddingTop);
            mPath.lineTo(paddingLeft, paddingTop);
            mPath.close();
        } else {
            mPath.moveTo(paddingLeft, paddingTop);
            mPath.lineTo(paddingLeft, height - curvatureHeight - paddingBottom);
            mPath.cubicTo(paddingLeft, height - paddingBottom,
                    width / 2 - paddingRight, height - curvatureHeight - paddingBottom,
                    width - paddingRight, height - paddingBottom);
            mPath.lineTo(width - paddingRight, paddingTop);
            mPath.lineTo(width - paddingRight, paddingTop);
            mPath.lineTo(paddingLeft, paddingTop);
            mPath.close();
        }
        return mPath;
    }

    static Path getClipPath(int width, int height, int curvatureHeight, int direction,
                            int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {

        Path mPath = new Path();

        if (direction == CrescentoImageView.CurvatureDirection.OUTWARD) {
            mPath.moveTo(0, 0);
            mPath.lineTo(paddingLeft, paddingTop);
            mPath.lineTo(paddingLeft, height - curvatureHeight - paddingBottom);
            mPath.cubicTo(paddingLeft, height - curvatureHeight - paddingBottom,
                    width / 2 - paddingRight, height - paddingBottom,
                    width - paddingRight, height - curvatureHeight - paddingBottom);
            mPath.lineTo(width - paddingRight, paddingTop);
            mPath.lineTo(paddingLeft, paddingTop);
            mPath.lineTo(0, 0);
            mPath.lineTo(width, 0);
            mPath.lineTo(width, height);
            mPath.lineTo(0, height);
            mPath.close();

        } else {
            mPath.moveTo(0, 0);
            mPath.lineTo(paddingLeft, paddingTop);
            mPath.lineTo(paddingLeft, height - curvatureHeight - paddingBottom);
            mPath.cubicTo(paddingLeft, height - paddingBottom,
                    width / 2 - paddingRight, height - curvatureHeight - paddingBottom,
                    width - paddingRight, height - paddingBottom);
            mPath.lineTo(width - paddingRight, paddingTop);
            mPath.lineTo(paddingLeft, paddingTop);
            mPath.lineTo(0, 0);
            mPath.lineTo(width, 0);
            mPath.lineTo(width, height);
            mPath.lineTo(0, height);
            mPath.close();
        }
        return mPath;
    }
}
