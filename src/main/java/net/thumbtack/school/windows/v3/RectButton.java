package net.thumbtack.school.windows.v3;

import net.thumbtack.school.windows.v3.base.RectWindow;

import java.util.Objects;

public class RectButton extends RectWindow {
    private String text;

    public RectButton(Point topLeft, Point bottomRight, boolean active, String text) {
        super(topLeft, bottomRight, active);
        this.text = text;
    }

    public RectButton(int xLeft, int yTop, int width, int height, boolean active, String text) {
        super(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), active);
        this.text = text;
    }

    public RectButton(Point topLeft, Point bottomRight, String text) {
        super(topLeft, bottomRight, true);
        this.text = text;
    }

    public RectButton(int xLeft, int yTop, int width, int height, String text) {
        super(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), true);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RectButton that = (RectButton) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}
