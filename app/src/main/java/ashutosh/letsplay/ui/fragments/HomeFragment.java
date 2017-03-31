package ashutosh.letsplay.ui.fragments;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ashutosh.letsplay.R;
import ashutosh.letsplay.application.BaseFragment;

/**
 * Created by ashutosh on 30/3/17.
 */

public class HomeFragment extends BaseFragment implements  LoaderManager.LoaderCallbacks<List<String>>{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.home_fragment, container, false);
        findAllViews(v);
        return v;
    }

    private void findAllViews(ViewGroup v) {

    }


    private void getCategoryList() {
        Bundle b = new Bundle();
        getActivity().getLoaderManager().restartLoader(0, b, this);
    }

    @Override
    public Loader<List<String>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<String>> loader) {

    }
}
