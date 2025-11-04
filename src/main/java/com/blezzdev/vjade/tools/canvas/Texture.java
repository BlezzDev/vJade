package com.blezzdev.vjade.tools.canvas;

import com.blezzdev.vjade.util.types.Filter;

public class Texture extends Image {
    private Filter filter = Filter.LINEAR;

    private boolean vFlip = false, hFlip = false;
    private int vDivs = 1, hDivs = 1;
    private int frame = 0;

    @Override
    public void load() {
        loader.load(getResourcePath(), getSize(), Filter.LINEAR, vFlip);
    }

    public Texture(String path) {
        super(path);
    }

    public Texture setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Texture setHorizontalDivisions(int h) {
        this.hDivs = h;
        return this;
    }
    public Texture setVerticalDivisions(int v) {
        this.vDivs = v;
        return this;
    }
    public Texture setDivisions(int v, int h) {
        this.vDivs = v;
        this.hDivs = h;
        return this;
    }
    public Texture setHorizontalFlip(boolean h) {
        this.hFlip = h;
        return this;
    }
    public Texture setVerticalFlip(boolean v) {
        this.vFlip = v;
        return this;
    }
    public Texture setFlip(boolean v, boolean h) {
        this.vFlip = v;
        this.hFlip = h;
        return this;
    }
    public Texture setFrame(int frame) {
        this.frame = frame;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public int getHorizontalDivisions() {
        return hDivs;
    }
    public int getVerticalDivisions() {
        return vDivs;
    }
    public boolean getHorizontalFlip() {
        return hFlip;
    }
    public boolean getVerticalFlip() {
        return vFlip;
    }
    public int getFrame() {
        return frame;
    }
}