package com.intive.toz.homescreen.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.intive.toz.Pet;
import com.intive.toz.R;

import java.util.List;

/**
 *  Adapter for recycler view containing list of pets.
 */
public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.ViewHolder> {

    private List<Pet> petsList;

    /**
     *
     * @param petsList List of pets
     */
    public PetsAdapter(final List<Pet> petsList) {
        this.petsList = petsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pet_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Context context = holder.img.getContext();
        holder.title.setText(petsList.get(position).getName());
        holder.description.setText(petsList.get(position).getDescription());
        holder.date.setText(petsList.get(position).getDate());
        Glide.with(context)
                .load(petsList.get(position).getProfilePic())
                .placeholder(R.color.colorAccent)
                .error(R.color.colorPrimaryDark)
                .into(holder.img);
    }


    /**
     *  Class for setting up views in a recycler view.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView title, description, date;

        /**
         * @param view the root view of a fragment
         */
        public ViewHolder(final View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.pet_img);
            title = (TextView) view.findViewById(R.id.title_pet);
            description = (TextView) view.findViewById(R.id.description_pet);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    @Override
    public int getItemCount() {
        return petsList.size();
    }
}
