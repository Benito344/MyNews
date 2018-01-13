package com.behague.benjamin.mynews.Modal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.behague.benjamin.mynews.R;

import java.util.ArrayList;


/**
 * Created by Benjamin BEHAGUE on 11/01/2018.
 */

public class ArticleAdaptaterTop extends RecyclerView.Adapter<ArticleAdaptaterTop.MyViewHolder> {

    public static ArrayList<Article> articles = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView article_title, article_date, article_abstract;

        public MyViewHolder(View view) {
            super(view);
            article_title = view.findViewById(R.id.article_title);
            article_abstract = view.findViewById(R.id.article_abstract);
            article_date = view.findViewById(R.id.article_date);
        }

    }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.article_list_row_top, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Article article = articles.get(position);
            holder.article_title.setText(article.getTitle());
            holder.article_abstract.setText(article.getAbstact());
            holder.article_date.setText(article.getPublishedDate());
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }


         public void update (ArrayList<Article> list){
             articles.clear();
             articles.addAll(list);

        }
}
