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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FitnessLogAdapter extends ArrayAdapter<FitnessLog> {

    private LayoutInflater mInflater;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public FitnessLogAdapter(Context context, int resource, List<FitnessLog> objects) {
        super(context, resource, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView thumbnailView;
        TextView mainText;
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

        Date date = log.getDate();
        String str = sdf.format(date);
        mainText = convertView.findViewById(R.id.maintext);
        if (null != mainText) {
            mainText.setText(str);
        }

        return convertView;
    }
}
