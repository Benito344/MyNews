package com.behague.benjamin.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.behague.benjamin.mynews.Modal.ArticleAdaptaterTop;
import com.behague.benjamin.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArticleAdaptaterTop mAdapter;

    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_top_stories, container, false);
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);

        mAdapter = new ArticleAdaptaterTop();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
