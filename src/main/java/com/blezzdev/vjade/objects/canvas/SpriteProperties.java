package com.blezzdev.vjade.objects.canvas;

public interface SpriteProperties<T> {
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
