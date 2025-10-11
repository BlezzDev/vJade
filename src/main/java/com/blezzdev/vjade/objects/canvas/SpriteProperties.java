package com.blezzdev.vjade.objects.canvas;

interface SpriteProperties<T> {
    class Properties {
        boolean horizontalFlip = false, verticalFlip = false;
        int frame = 0, horizontalDivisions = 0, verticalDivisions = 0;

        public void setFrame(int frame) {
            this.frame = frame;
        }

        public void setHorizontalDivisions(int horizontalDivisions) {
            this.horizontalDivisions = horizontalDivisions;
        }

        public void setHorizontalFlip(boolean horizontalFlip) {
            this.horizontalFlip = horizontalFlip;
        }

        public void setVerticalDivisions(int verticalDivisions) {
            this.verticalDivisions = verticalDivisions;
        }

        public void setVerticalFlip(boolean verticalFlip) {
            this.verticalFlip = verticalFlip;
        }

        public void setFlips(boolean vertical, boolean horizontal) {
            setVerticalFlip(vertical);
            setHorizontalFlip(horizontal);
        }

        public void setDivisions(int vertical, int horizontal) {
            setVerticalDivisions(vertical);
            setHorizontalDivisions(horizontal);
        }

        public int getFrame() {
            return frame;
        }

        public int getHorizontalDivisions() {
            return horizontalDivisions;
        }

        public int getVerticalDivisions() {
            return verticalDivisions;
        }

        public boolean isHorizontalFlip() {
            return horizontalFlip;
        }

        public boolean isVerticalFlip() {
            return verticalFlip;
        }
    }

    Properties properties = new Properties();

    @SuppressWarnings("unchecked")
    default T setFrame(int frame) {
        properties.setFrame(frame);

        return (T) this;
    }

    @SuppressWarnings("unchecked")
    default T setHorizontalFlip(boolean flipped) {
        properties.setHorizontalFlip(flipped);

        return (T) this;
    }

    @SuppressWarnings("unchecked")
    default T setVerticalFlip(boolean flipped) {
        properties.setVerticalFlip(flipped);

        return (T) this;
    }

    @SuppressWarnings("unchecked")
    default T setHorizontalDivisions(int divs) {
        properties.setHorizontalDivisions(divs);

        return (T) this;
    }

    @SuppressWarnings("unchecked")
    default T setDivisions(int div_v, int div_h) {
        properties.setDivisions(div_v, div_h);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    default T setFlips(int flip_v, int flip_h) {
        properties.setDivisions(flip_v, flip_h);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    default T setVerticalDivisions(int divs) {
        properties.setVerticalDivisions(divs);

        return (T) this;
    }

    default int getFrame() {
        return properties.getFrame();
    }

    default int getHorizontalDivisions() {
        return properties.getHorizontalDivisions();
    }

    default int getVerticalDivisions() {
        return properties.getVerticalDivisions();
    }

    default boolean isHorizontalFlip() {
        return properties.isHorizontalFlip();
    }

    default boolean isVerticalFlip() {
        return properties.isVerticalFlip();
    }
}
