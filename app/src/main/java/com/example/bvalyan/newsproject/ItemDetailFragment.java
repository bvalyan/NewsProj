package com.example.bvalyan.newsproject;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.bvalyan.newsproject.dummy.DummyContent;

import java.lang.reflect.Field;

import javax.microedition.khronos.opengles.GL;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_URL = "article_url";
    public static final String ARG_ITEM_IMG_URL = "article_image_url";
    public static final String ARG_ITEM_AUTHOR = "author";
    public static final String ARG_ITEM_DESC = "descrition";
    public static final String ARG_ITEM_SOURCE = "source";
    public static final String ARG_ITEM_DATE = "date" ;
    public static final String ARG_ITEM_TITLE = "title";
    public static boolean isTablet = false;

    /**
     * The dummy content this fragment is presenting.
     */
    private ArticleObject mItem = new ArticleObject();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_URL) && getArguments().containsKey(ARG_ITEM_IMG_URL)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem.setUrl(getArguments().getString(ARG_ITEM_URL));
            mItem.setImageURL(getArguments().getString(ARG_ITEM_IMG_URL));
            mItem.setPublishDate(getArguments().getString(ARG_ITEM_DATE));
            mItem.setDescription(getArguments().getString(ARG_ITEM_DESC));
            mItem.setSource(getArguments().getString(ARG_ITEM_SOURCE));
            mItem.setAuthor(getArguments().getString(ARG_ITEM_AUTHOR));
            mItem.setTitle(getArguments().getString(ARG_ITEM_TITLE));
            isTablet = getArguments().getBoolean("isTablet");
            Activity activity = this.getActivity();
            final CollapsingToolbarLayout colBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            AppBarLayout layout = activity.findViewById(R.id.app_bar);
            if (colBarLayout != null) {
                //TextView title = activity.findViewById(R.id.toolbar_title);
                //title.setSelected(true);
                //title.setText(mItem.getTitle());
                colBarLayout.setTitle("Provided by: "  + mItem.getSource());

                layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    boolean isShow = true;
                    int scrollRange = -1;
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                      /*  if (scrollRange == -1) {
                            scrollRange = appBarLayout.getTotalScrollRange();
                        }
                        if (scrollRange + verticalOffset == 0) {
                            colBarLayout.setTitle(mItem.getTitle());
                            isShow = true;
                        } else if(isShow) {
                            colBarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                            isShow = false;
                        }
                    */}
                });
                GlideApp.with(getContext()).load(mItem.getImageURL()).placeholder(R.drawable.news_1).into((ImageView) activity.findViewById(R.id.background));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.getDescription());
            ImageView imageView = (ImageView) rootView.findViewById(R.id.detail_image);
            if(isTablet) {
                GlideApp.with(rootView.getContext()).load(mItem.getImageURL()).centerCrop().placeholder(R.drawable.news_1).into(imageView);
            }
            else {
                imageView.setVisibility(View.GONE);
            }



        }

        return rootView;
    }
}
