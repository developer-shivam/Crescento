package developer.shivam.crescento;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import developer.shivam.library.CrescentoImageView;

public class MainActivity extends AppCompatActivity {

    CrescentoImageView sivSampleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sivSampleImage = (CrescentoImageView) findViewById(R.id.crescentoImageView);
    }
}
