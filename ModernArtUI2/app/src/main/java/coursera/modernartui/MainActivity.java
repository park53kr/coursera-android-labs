package coursera.modernartui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SeekBar seekBar;
    private TextView leftTop, leftBottom, rightTop, rightMiddle, rightBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        leftTop = (TextView) findViewById(R.id.lefttop);
        leftBottom = (TextView) findViewById(R.id.leftbottom);
        rightTop = (TextView) findViewById(R.id.righttop);
        rightMiddle = (TextView) findViewById(R.id.rightmiddle);
        rightBottom = (TextView) findViewById(R.id.rightbottom);

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int color;
                changeBackgroundColor(leftTop, i);
                changeBackgroundColor(leftBottom, i);
                changeBackgroundColor(rightTop, i);
                changeBackgroundColor(rightMiddle, i);
                changeBackgroundColor(rightBottom, i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void changeBackgroundColor(TextView textView, int i) {
        int color = getBackgroundColor(textView);
        textView.setBackgroundColor(color - i * 10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //TODO: Need to add action poping up dialog
            showDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int getBackgroundColor(TextView textView) {
        ColorDrawable drawable = (ColorDrawable) textView.getBackground();
        if (Build.VERSION.SDK_INT >= 11) {
            return drawable.getColor();
        }
        try {
            Field field = drawable.getClass().getDeclaredField("mState");
            field.setAccessible(true);
            Object object = field.get(drawable);
            field = object.getClass().getDeclaredField("mUseColor");
            field.setAccessible(true);
            return field.getInt(object);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 0;
    }

    public void doPositiveClick() {
        String q = getResources().getString(R.string.url);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(q));
        startActivity(intent);
    }

    public void showDialog(){
        DialogFragment newFragment = MyDialogFragment.newInstance(R.string.dialog_title);
        newFragment.show(getFragmentManager(), "dialog");
    }
}


