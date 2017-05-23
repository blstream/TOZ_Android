package com.intive.toz.petslist.view;

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
import com.intive.toz.network.ApiClient;
import com.intive.toz.petDetails.view.PetDetailsActivity;
import com.intive.toz.petslist.model.Pet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for recycler view containing list of pets.
 */
public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.ViewHolder> {

    private List<Pet> petsList;

    /**
     * @param petsList List of pets
     */
    public PetsAdapter(final List<Pet> petsList) {
        this.petsList = petsList;
    }


    /**
     * Setter for pets List.
     *
     * @param petsList list of Pet objects
     */
    public void setPetsList(final List<Pet> petsList) {
        this.petsList = petsList;
    }

    /**
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
        holder.type.setText(petsList.get(position).getType());

        String type = petsList.get(position).getType();
        int whichType = type.contains("DOG") ? R.drawable.dog : R.drawable.cat;

        Glide.with(context)
                .load(ApiClient.IMAGES_BASE_URL + petsList.get(position).getImageUrl())
                .placeholder(whichType)
                .error(whichType)
                .into(holder.img);
    }


    /**
     * Class for setting up views in a recycler view.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title_tv)
        TextView title;

        @BindView(R.id.animal_type_tv)
        TextView type;

        @BindView(R.id.news_iv)
        ImageView img;

        /**
         * @param view the root view of a fragment
         */
        public ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            int position = getAdapterPosition();

            String pet = petsList.get(position).getId();
            String name = petsList.get(position).getName();
            Intent intent = new Intent(view.getContext(), PetDetailsActivity.class);
            intent.putExtra("petKey", pet);
            intent.putExtra("petName", name);
            view.getContext().startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return petsList.size();
    }
}
