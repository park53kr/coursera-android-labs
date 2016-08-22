
package course.labs.asynctasklab;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class DownloaderTaskFragment extends Fragment {

	private DownloadFinishedListener mCallback;
	private Context mContext;
	private ProgressDialog progressDialog;

	static final String TAG_FRIEND_RES_IDS = "friends";
	@SuppressWarnings("unused")
	private static final String TAG = "Lab-Threads";
	DownloaderTask downloaderTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Preserve across reconfigurations
		setRetainInstance(true);

		// TODO: Create new DownloaderTask that "downloads" data
		//Bundle args = savedInstanceState;
		downloaderTask = new DownloaderTask();
		//new DownloaderTask().execute(args.getIntegerArrayList(TAG_FRIEND_RES_IDS));
		Bundle args = getArguments();

		// TODO: Retrieve arguments from DownloaderTaskFragment
		// Prepare them for use with DownloaderTask.
		ArrayList<Integer> integers = args.getIntegerArrayList(TAG_FRIEND_RES_IDS);
		Integer[] arrayInteger = new Integer[integers.size()];
		int j = 0;
		for (Integer i : integers) {
			arrayInteger[j] = i;
			j++;
		}

		// TODO: Start the DownloaderTask
		downloaderTask.execute(arrayInteger);
	}

	// Assign current hosting Activity to mCallback
	// Store application context for use by downloadTweets()
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity.getApplicationContext();

		// Make sure that the hosting activity has implemented
		// the correct callback interface.
		try {
			mCallback = (DownloadFinishedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement DownloadFinishedListener");
		}
	}

	// Null out mCallback
	@Override
	public void onDetach() {
		super.onDetach();
		mCallback = null;
	}
	//Param, Progress, Result

	// TODO: Implement an AsyncTask subclass called DownLoaderTask.
	// This class must use the downloadTweets method (currently commented
	// out). Ultimately, it must also pass newly available data back to
	// the hosting Activity using the DownloadFinishedListener interface.

	public class DownloaderTask extends AsyncTask<Integer, Integer, String[]> {

		//핵심 연산을 처리하는 함수
		//다운로드를 받아야 함.

		public DownloaderTask() {
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage("Loading...");

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog.show();
		}

		@Override
		protected String[] doInBackground(Integer... values) {
			String[] feeds = downloadTweets(values);
			return feeds;
		}


		@Override
		protected void onPostExecute(String[] strings) {
			super.onPostExecute(strings);
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
			mCallback.notifyDataRefreshed(strings);


		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);

		}

		@Override
		protected void onCancelled(String[] strings) {
			super.onCancelled(strings);
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	}


	// TODO: Uncomment this helper method
	// Simulates downloading Twitter data from the network

	@Override
	public void onResume() {
		super.onResume();

	}

	private String[] downloadTweets(Integer resourceIDS[]) {
		final int simulatedDelay = 1000;


		String[] feeds = new String[resourceIDS.length];
		try {
			for (int idx = 0; idx < resourceIDS.length; idx++) {

				InputStream inputStream;
				BufferedReader in;
				try {
					// Pretend downloading takes a long time
					Thread.sleep(simulatedDelay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				inputStream = mContext.getResources().openRawResource(
						resourceIDS[idx]);
				in = new BufferedReader(new InputStreamReader(inputStream));

				String readLine;
				StringBuffer buf = new StringBuffer();

				while ((readLine = in.readLine()) != null) {
					buf.append(readLine);
				}

				feeds[idx] = buf.toString();

				if (null != in) {
					in.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return feeds;
	}
}