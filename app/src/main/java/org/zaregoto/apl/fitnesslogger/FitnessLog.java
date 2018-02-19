package org.zaregoto.apl.fitnesslogger;

import android.graphics.Bitmap;

import java.io.File;
import java.util.Date;

public class FitnessLog {

    private Date date;
    private Bitmap thumbnail;
    private File photo;

    public FitnessLog(Date date, Bitmap thumbnail, File photo) {
        this.date = date;
        this.thumbnail = thumbnail;
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }
}
