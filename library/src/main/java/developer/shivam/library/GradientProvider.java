package developer.shivam.library;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;

class GradientProvider {

    static Shader getShader(int height) {
        return new LinearGradient(0, 0, 0, height, Color.TRANSPARENT, Color.parseColor("#000000"), Shader.TileMode.CLAMP);
    }
}
