package course.labs.asynctasklab;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FriendsFragment extends ListFragment {

	// HostingActivity
	private SelectionListener mCallback;
	//TODO: 1. ListView xml setLayout 2. CustomAdapter 3. item class(Model) 4. listview.setAdapter(adapter)
	ListView listView = null;
	FbListViewAdapter fbListViewAdapter = null;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return super.onCreateView(inflater, container, savedInstanceState);
//		View view = inflater.inflate(R.layout.list_view, null);
//		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onActivityCreated(savedInstanceState);
		fbListViewAdapter = new FbListViewAdapter(getActivity(),R.layout.list_item);
		fbListViewAdapter.add(new ListData("Junsuk", "Hello World", null));
		//fbListViewAdapter.notifyDataSetChanged();
		setListAdapter(fbListViewAdapter);
//

//		setListAdapter(new ArrayAdapter<String>(getActivity(),
//				android.R.layout.simple_list_item_1, MainActivity.FRIENDS_NAMES));

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Make sure that the hosting Activity has implemented
		// the callback interface. 
		try {
			mCallback = (SelectionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement SelectionListener");
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// Enable user interaction only if data is available
		super.onActivityCreated(savedInstanceState);
		setAllowUserClicks(mCallback.canAllowUserClicks());
	}

	// Enable/disable user interaction
	void setAllowUserClicks(boolean allowUserInteraction) {
		getListView().setEnabled(allowUserInteraction);
		if (allowUserInteraction) {
			getListView().setBackgroundColor(Color.WHITE);

		} else {
			getListView().setBackgroundColor(Color.DKGRAY);
		}
	}

	@Override
	public void onListItemClick(ListView l, View view, int position, long id) {
		// Inform hosting Activity of user's selection
		if (null != mCallback) {
			mCallback.onItemSelected(position);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d("Friend", "resume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d("Friend", "pause");
	}
}
