package com.example.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.news.R;
import com.example.news.di.ApiModule;
import com.example.news.di.AppComponent;
import com.example.news.di.DaggerAppComponent;
import com.example.news.model.News;
import com.example.news.network.Service;
import com.example.news.presenter.NewsPresenter;
import com.example.news.view.NewsAdapter;
import com.example.news.view.NewsView;

import javax.inject.Inject;


/**
 * Created by xiecy on 2018/01/30.
 */

public class BitcoinFragment extends Fragment implements NewsView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MaterialRefreshLayout refresh;


    @Inject
    public Service service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news,container,false);

        AppComponent appComponent= DaggerAppComponent.builder().apiModule(new ApiModule()).build();
        appComponent.inject(this);



        recyclerView = view.findViewById(R.id.recycler);
        progressBar= view.findViewById(R.id.progress);
        refresh=view.findViewById(R.id.refresh);



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final NewsPresenter presenter=new NewsPresenter(service,this);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                    presenter.getBitCoinNews();
                    refresh.finishRefresh();
            }
        });
        presenter.getBitCoinNews();
        return view;

    }


    @Override
    public void showProgress() {
         progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeProgress() {
         progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNews(News news) {
        NewsAdapter adapter=new NewsAdapter(news.getArticles(),getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showFailure(String errorMessage) {
          Toast.makeText(getActivity(),errorMessage,Toast.LENGTH_SHORT).show();
    }

}
