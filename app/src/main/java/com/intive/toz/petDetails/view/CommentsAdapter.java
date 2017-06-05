package com.intive.toz.petDetails.view;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.intive.toz.R;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.data.DateFormatter;
import com.intive.toz.login.Session;
import com.intive.toz.petDetails.model.Comment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type Comments adapter.
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    private List<Comment> comments;
    private DateFormatter formatter;

    /**
     * Instantiates a new Comments adapter.
     */
    public CommentsAdapter() {
        formatter = new DateFormatter();
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentsViewHolder holder, final int position) {
        String author = comments.get(position).getAuthorName() + " " + comments.get(position).getAuthorSurname();
        holder.author.setText(author);
        holder.content.setText(comments.get(position).getContents());
        String date = "| " + formatter.convertToDate(comments.get(position).getCreated());
        holder.date.setText(date);

        if (comments.get(position).getUserUuid().equals(Session.getUserId())) {
            holder.editBtn.setVisibility(View.VISIBLE);
        } else {
            holder.editBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return comments == null ? 0 : comments.size();
    }

    /**
     * The type Comments view holder.
     */
    final class CommentsViewHolder extends RecyclerView.ViewHolder {

        /**
         * The Author.
         */
        @BindView(R.id.author_tv)
        TextView author;

        /**
         * The Date.
         */
        @BindView(R.id.date_tv)
        TextView date;

        /**
         * The Content.
         */
        @BindView(R.id.content_tv)
        TextView content;

        /**
         * The Edit btn.
         */
        @BindView(R.id.edit_btn)
        TextView editBtn;

        /**
         * The Add comment view.
         */
        @BindView(R.id.add_comment_view)
        View addCommentView;

        /**
         * The Add comment et.
         */
        @BindView(R.id.add_comment_et)
        TextInputEditText addCommentEt;

        /**
         * The Add comment btn.
         */
        @BindView(R.id.add_comment)
        TextView addCommentBtn;

        /**
         * Instantiates a new Comments view holder.
         *
         * @param itemView the item view
         */
        CommentsViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    addCommentView.setVisibility(View.VISIBLE);
                    addCommentEt.setText(content.getText());
                }
            });

            addCommentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Comment c = comments.get(getAdapterPosition());
                    c.setContents(addCommentEt.getText().toString());
                    new DataLoader().editComment(new DataProvider.ResponseCallback<Comment>() {
                        @Override
                        public void onSuccess(final Comment response) {
                            content.setText(response.getContents());
                            addCommentView.setVisibility(View.GONE);
                            addCommentEt.clearFocus();
                            InputMethodManager inputManager = (InputMethodManager)
                                    v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                            inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);

                            addCommentEt.setText(null);
                        }

                        @Override
                        public void onError(final Throwable e) {
                            Toast.makeText(v.getContext(), R.string.default_error, Toast.LENGTH_SHORT).show();
                        }
                    }, c);
                }
            });
        }
    }
}
