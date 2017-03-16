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

import butterknife.BindView;
import butterknife.ButterKnife;

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


    public void setPetsList(final List<Pet> petsList) {
        this.petsList = petsList;
    }

    /**
     *
     * @return list of Pet objects
     */
    public List<Pet> getPetsList() {
        return petsList;
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

        @BindView(R.id.pet_img)
        ImageView img;

        @BindView(R.id.title_pet)
        TextView title;

        @BindView(R.id.description_pet)
        TextView description;

        @BindView(R.id.date)
        TextView date;

        /**
         * @param view the root view of a fragment
         */
        public ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {
        return petsList.size();
    }
}
