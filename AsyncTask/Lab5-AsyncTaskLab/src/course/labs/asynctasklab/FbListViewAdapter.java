package course.labs.asynctasklab;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Junsuk on 2016-08-21.
 */
public class FbListViewAdapter extends BaseAdapter {

    private ArrayList<ListData> listItems = new ArrayList<>();
    private ViewHolder viewHolder;
    private Context mContext;
    private int mResource;

    public FbListViewAdapter(Context context, int resource) {

        mContext = context;
        mResource = resource;
    }

    public class ViewHolder {
        public ImageView imageView;
        public TextView facebookId;
        public TextView facebookFeed;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public ListData getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mResource, null);
            viewHolder.facebookId = (TextView) convertView.findViewById(R.id.facebook_id);
            Log.d("ID", (String) viewHolder.facebookId.getText());
            viewHolder.facebookFeed = (TextView) convertView.findViewById(R.id.facebook_feed);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.facebook_profile);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListData listData = listItems.get(position);

        if (listData.profilePhoto != null) {
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.imageView.setImageDrawable(listData.profilePhoto);
        }
        else{
            viewHolder.imageView.setVisibility(View.GONE);
        }
        viewHolder.facebookId.setText(listData.facebookId);
        viewHolder.facebookFeed.setText(listData.facebookFeed);

        return convertView;
    }

    public void add(ListData listData) {
        listItems.add(listData);
    }
}
