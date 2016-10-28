package br.com.simplepass.curriculo.domain;

import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * Created by leandro on 6/3/16.
 */
public class Project {
    private String name;
    private String description;
    private String date;
    private Uri storeUri;
    private Drawable drawable;

    public Project(String name, String description, String date, Uri storeUri, Drawable drawable) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.storeUri = storeUri;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Uri getStoreUri() {
        return storeUri;
    }

    public void setStoreUri(Uri storeUri) {
        this.storeUri = storeUri;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
