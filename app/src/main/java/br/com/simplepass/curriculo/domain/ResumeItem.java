package br.com.simplepass.curriculo.domain;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by leandro on 6/1/16.
 */
public class ResumeItem {
    String name;
    String description;
    Intent intent;
    Drawable drawable;

    public ResumeItem(String name, String description, Intent intent, Drawable drawable) {
        this.name = name;
        this.description = description;
        this.intent = intent;
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

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
