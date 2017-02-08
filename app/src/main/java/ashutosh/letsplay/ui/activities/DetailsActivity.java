package ashutosh.letsplay.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import ashutosh.letsplay.R;
import ashutosh.letsplay.application.BaseActivity;
import ashutosh.letsplay.util.AppConstant;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ashutosh on 20/1/17.
 */

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.tv_dl_title)
    TextView mTvTitle;
    @BindView(R.id.tv_dl_chords)
    TextView mTvTabAndChords;
    @BindView(R.id.cv_link)
    CardView mCvLink;
    @BindView(R.id.tv_link)
    TextView mTvLink;
    @BindView(R.id.tv_dl_Tags)
    TextView mTvTags;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        ButterKnife.bind(this);
        mTvTitle.setText(getIntent().getStringExtra(AppConstant.TITLE));
        mTvTabAndChords.setText(Html.fromHtml(getIntent().getStringExtra(AppConstant.TABS_AND_CHORDS)));

        mTvLink.setText(getIntent().getStringExtra(AppConstant.LINK));
        mTvTags.setText(getIntent().getStringExtra(AppConstant.TAGS));

    }

    public void onClickView(View v) {

    }

}
