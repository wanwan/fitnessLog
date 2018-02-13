package org.zaregoto.apl.fitnesslogger;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class FitnessLogAdapter extends ArrayAdapter<FitnessLog> {

    private LayoutInflater mInflater;

    public FitnessLogAdapter(Context context, int resource, List<FitnessLog> objects) {
        super(context, resource, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView thumbnailView;
        FitnessLog log;

        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.photo_listitem, null);
        }

        log = getItem(position);
        thumbnailView = convertView.findViewById(R.id.photo);
        if (null != thumbnailView && null != log) {
            Bitmap bitmap = log.getThumbnail();
            if (null != bitmap) {
                Resources r = getContext().getResources();
                Drawable d = new BitmapDrawable(r, bitmap);
                thumbnailView.setImageDrawable(d);
            }
        }

        return convertView;
    }
}
