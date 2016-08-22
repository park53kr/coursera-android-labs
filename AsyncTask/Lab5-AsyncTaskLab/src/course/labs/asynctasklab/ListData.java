package course.labs.asynctasklab;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Junsuk on 2016-08-21.
 */
public class ListData {
    public String facebookId=null;
    public String facebookFeed=null;
    public Drawable profilePhoto=null;

    public ListData(String facebookId, String facebookFeed, Drawable profilePhoto) {
        this.facebookId = facebookId;
        this.facebookFeed = facebookFeed;
        this.profilePhoto = profilePhoto;
    }
}
