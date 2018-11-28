package net.thumbtack.school.windows.v4.base;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.Point;
import net.thumbtack.school.windows.v4.iface.Movable;
import net.thumbtack.school.windows.v4.iface.Resizable;

import java.util.Objects;

public abstract class Window implements Movable, Resizable {
    private WindowState state;

    public Window(WindowState state) throws WindowException {
        if (state == null || state == WindowState.DESTROYED)
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        this.state = state;
    }

    public Window(String state) throws WindowException {
        if (state == null || state == WindowState.DESTROYED.toString())
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        this.state = WindowState.fromString(state);
    }

    public WindowState getState() {
        return state;
    }

    public void setState(WindowState state) throws WindowException {
        if (this.state == WindowState.DESTROYED && state != WindowState.DESTROYED)
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        this.state = state;
    }

    public abstract boolean isInside(int x, int y);

    public abstract boolean isInside(Point point);

    public abstract boolean isFullyVisibleOnDesktop(Desktop desktop);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Window window = (Window) o;
        return state == window.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
