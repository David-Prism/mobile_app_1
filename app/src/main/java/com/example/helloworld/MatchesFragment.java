package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import android.location.LocationManager;
import android.location.LocationListener;
import android.location.Location;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class MatchesFragment extends Fragment {

    private static final String TAG = MatchesFragment.class.getSimpleName();

    private LocationManager locationManager;
    private double longitudeNetwork, latitudeNetwork;
    private TextView longitudeValueNetwork, latitudeValueNetwork;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view,
                container, false);

        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        longitudeValueNetwork = recyclerView.findViewById(R.id.longitudeValueNetwork);
        latitudeValueNetwork = recyclerView.findViewById(R.id.latitudeValueNetwork);

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView picture;
        public TextView name;
        public ImageButton likeButton;


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.matches_fragment, parent, false));

            picture = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.card_title);

            likeButton = itemView.findViewById(R.id.like_button);

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String likeToast = String.format(itemView.getContext().getString(R.string.you_liked),
                            name.getText().toString());

                    Toast toast = Toast.makeText(v.getContext(), likeToast, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {

        private int mLength;
        private String[] mNames;
        private String[] mPictures;
        private String[] mUid;
        private boolean[] mIsLiked;
        private String[] mLat;
        private String[] mLong;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();

            MatchesViewModel viewModel = new MatchesViewModel();

            viewModel.getMatches(
                    (ArrayList<Match> matches) -> {
                        int len = matches.size();

                        mNames = new String[len];
                        mPictures = new String[len];
                        mUid = new String[len];
                        mIsLiked = new boolean[len];
                        mLat = new String[len];
                        mLong = new String[len];
                        mLength = matches.size();

                        for(int i = 0; i < len; i++) {
                            mNames[i] = matches.get(i).getName();
                            mPictures[i] = matches.get(i).getImageUrl();
                            mUid[i] = matches.get(i).getUid();
                            mIsLiked[i] = matches.get(i).getIsLiked();
                            mLat[i] = matches.get(i).getLat();
                            mLong[i] = matches.get(i).getLongitude();
                        }
                        notifyDataSetChanged();
                    }
            );
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(mNames[position % mNames.length]);
            Picasso.get().load(mPictures[position % mPictures.length]).into(holder.picture);
        }

        @Override
        public int getItemCount() {
            return mLength;
        }
    }

    // Location services methods -------------------------------

    private boolean checkLocation() {
        if(!isLocationEnabled()) {
            showAlert(getContext());
        }
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showAlert(Context context) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        dialog.setTitle(R.string.enable_location)
                .setMessage(context.getResources().getString(R.string.location_message))
                .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                })
                .setNegativeButton(R.string.cancel, (paramDialogInterface, paramInt) -> {});
        dialog.show();
    }

    public void toggleNetworkUpdates(View view) {
        if(!checkLocation()) {
            return;
        }
        Button button = (Button) view;
        if(button.getText().equals(getResources().getString(R.string.pause))) {
            locationManager.removeUpdates(locationListenerNetwork);
            button.setText(R.string.resume);
        }
        else {
            if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60 * 1000, 10, locationListenerNetwork);
                Toast.makeText(getContext(), getString(R.string.network_provider_started_running), Toast.LENGTH_LONG).show();
                button.setText(R.string.pause);
            }
        }
    }

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();

            getActivity().runOnUiThread(()-> {
                longitudeValueNetwork.setText(String.format("%s", longitudeNetwork));
                latitudeValueNetwork.setText(String.format("%s", latitudeNetwork));
                Toast.makeText(getContext(), R.string.network_provider_update, Toast.LENGTH_SHORT).show();
            });
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

}
