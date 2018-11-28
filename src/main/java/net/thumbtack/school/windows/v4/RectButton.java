package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.RectWindow;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

import java.util.Objects;

public class RectButton extends RectWindow {
    private String text;

    public RectButton(Point topLeft, Point bottomRight, WindowState state, String text) throws WindowException {
        super(topLeft, bottomRight, state);
        this.text = text;
    }

    public RectButton(Point topLeft, Point bottomRight, String state, String text) throws WindowException {
        super(topLeft, bottomRight, state);
        this.text = text;
    }

    public RectButton(int xLeft, int yTop, int width, int height, WindowState state, String text) throws WindowException {
        super(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), state);
        this.text = text;
    }

    public RectButton(int xLeft, int yTop, int width, int height, String state, String text) throws WindowException {
        super(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), state);
        this.text = text;
    }

    public RectButton(Point topLeft, Point bottomRight, String text) throws WindowException {
        super(topLeft, bottomRight, WindowState.ACTIVE);
        this.text = text;
    }

    public RectButton(int xLeft, int yTop, int width, int height, String text) throws WindowException {
        super(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), WindowState.ACTIVE);
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
