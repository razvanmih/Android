package com.example.razva.searchshow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by razva on 14-Mar-18.
 */

public class TvShowAdapter extends ArrayAdapter<TvShow> {
    public TvShowAdapter(@NonNull Context context, ArrayList<TvShow> values) {
        super(context, R.layout.tv_show_small_layout, values);
    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.tv_show_small_layout, parent, false);

        TvShow show = getItem(position);

        TextView nameText = view.findViewById(R.id.nameText);
        nameText.setText(show.getName());


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(show.getFirst_air_date());

            TextView airDate = view.findViewById(R.id.dateText);
            format = new SimpleDateFormat(("MMMM dd, yyyy"));
            airDate.setText(format.format(date));

        }catch (Exception e)
        {}

        ImageView posterImage = view.findViewById(R.id.posterImage);
        Picasso.get().load("https://image.tmdb.org/t/p/original"+show.getPoster_path())
                .placeholder(R.drawable.placeholder).into(posterImage);


        TextView overview = view.findViewById(R.id.infoText);
        String overviewShort = "";
        if(show.getOverview().length()>138)
            overviewShort = show.getOverview().substring(0,138)+"...";

        overview.setText(overviewShort);


        return view;
    }
}
