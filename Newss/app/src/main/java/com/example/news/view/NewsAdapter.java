package com.example.news.view;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.news.NewsDetailActivity;
import com.example.news.R;
import com.example.news.di.GlideApp;
import com.example.news.model.News;

import java.util.List;

/**
 * Created by xiecy on 2018/01/26.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News.ArticlesBean> data;
    private Context context;




    public NewsAdapter(List<News.ArticlesBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context!=null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);

        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                News.ArticlesBean news=data.get(position);
                Intent intent=new Intent(context, NewsDetailActivity.class);
                intent.putExtra("url",news.getUrl());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.author.setText(data.get(position).getAuthor());
        holder.url.setText(data.get(position).getSource().getName());
        holder.time.setText(data.get(position).getPublishedAt());


        if (data.get(position).getUrlToImage()!=null) {
                String picUrl=data.get(position).getUrlToImage();

           // Glide.with(context).load(picUrl).placeholder(R.mipmap.image3).into(holder.imageView);
            GlideApp.with(context).load(picUrl).centerCrop().placeholder(R.mipmap.image3).into(holder.imageView);
        }else {
            GlideApp.with(context).load(R.mipmap.image3).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        TextView title,author,url,time;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            cardView=(CardView)view;
            title=view.findViewById(R.id.newsTitle);
            author=view.findViewById(R.id.author);
            url=view.findViewById(R.id.url);
            time=view.findViewById(R.id.publishedTime);
            imageView=view.findViewById(R.id.newsImage);
        }
    }
}
