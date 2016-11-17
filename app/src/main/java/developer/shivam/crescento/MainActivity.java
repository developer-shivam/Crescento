package developer.shivam.crescento;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import developer.shivam.library.CrescentoImageView;

public class MainActivity extends AppCompatActivity {

    SeekBar sbElevationController;
    CrescentoImageView sivSampleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sivSampleImage = (CrescentoImageView) findViewById(R.id.crescentoImageView);
        sbElevationController = (SeekBar) findViewById(R.id.sbElevationController);
        sbElevationController.setMax(50);
        sbElevationController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                if (fromUser) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        sivSampleImage.setElevation(i);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
