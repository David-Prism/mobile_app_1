package com.example.helloworld;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MatchesFragment extends Fragment {

    private static final String TAG = MatchesFragment.class.getSimpleName();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView picture;
        public TextView name;
        public TextView description;
        public ImageButton likeButton;
        public String likeMessage;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.matches_fragment, parent, false));
            picture = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.card_title);
            description = itemView.findViewById(R.id.card_text);
            likeButton = itemView.findViewById(R.id.like_button);
//            likeMessage = name.getText().toString();

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String likeToast = "You liked ";
                    likeToast = likeToast.concat(name.getText().toString());

                    Toast toast = Toast.makeText(v.getContext(), likeToast, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 3;
        private final String[] mNames;
        private final String[] mMatchDesc;
        private final Drawable[] mPictures;
        public ContentAdapter(Context context) {
            Resources resources = context.getResources();

            mNames = new String[LENGTH];
            mMatchDesc = new String[LENGTH];
            mPictures = new Drawable[LENGTH];

            mNames[0] = "Shmoople";
            mNames[1] = "Jessica";
            mNames[2] = "Ignatius";
            mMatchDesc[0] = "I hate my name.";
            mMatchDesc[1] = "I love cats!";
            mMatchDesc[2] = "This isn't where I parked my car!";
            mPictures[0] = context.getDrawable(R.drawable.fox_head);
            mPictures[1] = context.getDrawable(R.drawable.rhombicuboctohedron);
            mPictures[2] = context.getDrawable(R.drawable.small_axe);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageDrawable(mPictures[position % mPictures.length]);
            holder.name.setText(mNames[position % mNames.length]);
            holder.description.setText(mMatchDesc[position % mMatchDesc.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
