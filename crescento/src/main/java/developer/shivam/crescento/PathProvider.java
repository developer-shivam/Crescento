package developer.shivam.crescento;

import android.graphics.Path;

class PathProvider {

    static Path getOutlinePath(int width, int height, int curvatureHeight, int direction, int gravity) {

        Path mPath = new Path();

        if (direction == ImageView.CurvatureDirection.OUTWARD) {
            if (gravity == ImageView.Gravity.TOP) {
                mPath.moveTo(0, 0);
                mPath.lineTo(0, height - curvatureHeight);
                mPath.quadTo(width / 2, height + curvatureHeight,
                        width, height - curvatureHeight);
                mPath.lineTo(width, 0);
                mPath.lineTo(0, 0);
                mPath.close();
            } else {
                mPath.moveTo(0, height);
                mPath.lineTo(0, curvatureHeight);
                mPath.quadTo(width / 2, -curvatureHeight,
                        width, curvatureHeight);
                mPath.lineTo(width, height);
                mPath.close();
            }
        } else {
            if (gravity == ImageView.Gravity.TOP) {
                mPath.moveTo(0, 0);
                mPath.lineTo(0, height);
                mPath.quadTo(width / 2, height - curvatureHeight,
                        width, height);
                mPath.lineTo(width, 0);
                mPath.lineTo(0, 0);
                mPath.close();
            } else {
                mPath.moveTo(0, height);
                mPath.lineTo(0, 0);
                mPath.cubicTo(0, 0,
                        width / 2, curvatureHeight,
                        width, curvatureHeight);
                mPath.lineTo(width, height);
                mPath.lineTo(0, height);
                mPath.close();
            }
        }
        return mPath;
    }

    static Path getClipPath(int width, int height, int curvatureHeight, int direction, int gravity) {

        Path mPath = new Path();

        if (direction == ImageView.CurvatureDirection.OUTWARD) {
            if (gravity == ImageView.Gravity.TOP) {

                mPath.moveTo(0, height - curvatureHeight);
                mPath.quadTo(width / 2, height + curvatureHeight,
                        width, height - curvatureHeight);
                mPath.lineTo(width, 0);
                mPath.lineTo(width, height);
                mPath.lineTo(0, height);
                mPath.close();
            } else {
                mPath.moveTo(0, 0);
                mPath.lineTo(width, 0);
                mPath.lineTo(width, curvatureHeight);
                mPath.quadTo(width / 2, -curvatureHeight,
                        0, curvatureHeight);
                mPath.lineTo(0, 0);
                mPath.close();
            }
        } else {
            if (gravity == ImageView.Gravity.TOP) {
                mPath.moveTo(0, height);
                mPath.quadTo(width / 2, height - 2 * curvatureHeight,
                        width, height);
                mPath.lineTo(width, height);
                mPath.close();
            } else {
                mPath.moveTo(0, 0);
                mPath.lineTo(width, 0);
                mPath.quadTo(width / 2, 2 * curvatureHeight,
                        0, 0);
                mPath.lineTo(0, 0);
                mPath.close();
            }
        }
        return mPath;
    }
}
