package com.intive.toz.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.intive.toz.R;
import com.intive.toz.news.model.News;
import com.intive.toz.news_detail.view.NewsDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type News adapter.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<News> newsList;

    /**
     * Instantiates a new News adapter.
     */
    public NewsAdapter() {
    }

    /**
     * Sets news list.
     *
     * @param newsList the news list
     */
    public void setNewsList(final List<News> newsList) {
        this.newsList = newsList;
    }

    /**
     * Gets news list.
     *
     * @return the news list
     */
    public List<News> getNewsList() {
        return newsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        /*if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_header, parent, false);
            return new NewsHeaderViewHolder(view);
        }*/

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_row, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NewsViewHolder) {
            NewsViewHolder h = (NewsViewHolder) holder;
            h.titleTv.setText(newsList.get(position).getTitle());
            h.descriptionTv.setText(newsList.get(position).getContents());
            Context context = h.newsIv.getContext();
            Glide.with(context)
                    .load(newsList.get(position).getPhotoUrl())
                    .centerCrop()
                    .placeholder(R.color.colorAccent)
                    .error(R.color.colorPrimary)
                    .into(h.newsIv);
        }
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    @Override
    public int getItemViewType(final int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(final int position) {
        return position == 0;
    }


    /**
     * The type News view holder.
     */
    final class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * The Title tv.
         */
        @BindView(R.id.title_tv)
        TextView titleTv;

        /**
         * The Description tv.
         */
        @BindView(R.id.description_tv)
        TextView descriptionTv;

        /**
         * The News iv.
         */
        @BindView(R.id.news_iv)
        ImageView newsIv;

        /**
         * Instantiates a new News view holder.
         *
         * @param itemView the item view
         */
        private NewsViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            int position = getAdapterPosition();
            String id = newsList.get(position).getId();
            Intent i = new Intent(v.getContext(), NewsDetailActivity.class);
            i.putExtra(NewsDetailActivity.ID, id);
            v.getContext().startActivity(i);
        }
    }

    /**
     * The type News header view holder.
     */
    final class NewsHeaderViewHolder extends RecyclerView.ViewHolder {

        /**
         * Instantiates a new News header view holder.
         *
         * @param itemView the item view
         */
        private NewsHeaderViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
