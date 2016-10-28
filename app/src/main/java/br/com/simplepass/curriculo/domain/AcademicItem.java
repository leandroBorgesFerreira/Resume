package br.com.simplepass.curriculo.domain;

import android.graphics.drawable.Drawable;

/**
 * Created by leandro on 6/2/16.
 */
public class AcademicItem {
    String name;
    String conclusion;
    Drawable drawable;

    public AcademicItem(String name, String conclusion, Drawable drawable) {
        this.name = name;
        this.conclusion = conclusion;
        this.drawable = drawable;
    }

    public AcademicItem(String name, String conclusion) {
        this.name = name;
        this.conclusion = conclusion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
