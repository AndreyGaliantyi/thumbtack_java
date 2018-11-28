package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

import java.util.Objects;

public class RadioButton extends RoundButton {
    boolean checked;

    public RadioButton(Point center, int radius, WindowState state, String text, boolean checked) throws WindowException {
        super(center, radius, state, text);
        this.checked = checked;
    }

    public RadioButton(Point center, int radius, String state, String text, boolean checked) throws WindowException {
        super(center, radius, state, text);
        this.checked = checked;
    }

    public RadioButton(int xCenter, int yCenter, int radius, WindowState state, String text, boolean checked) throws WindowException {
        super(xCenter, yCenter, radius, state, text);
        this.checked = checked;
    }

    public RadioButton(int xCenter, int yCenter, int radius, String state, String text, boolean checked) throws WindowException {
        super(xCenter, yCenter, radius, state, text);
        this.checked = checked;
    }

    public RadioButton(Point center, int radius, String text, boolean checked) throws WindowException {
        super(center, radius, text);
        this.checked = checked;
    }

    public RadioButton(int xCenter, int yCenter, int radius, String text, boolean checked) throws WindowException {
        super(xCenter, yCenter, radius, text);
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RadioButton that = (RadioButton) o;
        return checked == that.checked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), checked);
    }
}
