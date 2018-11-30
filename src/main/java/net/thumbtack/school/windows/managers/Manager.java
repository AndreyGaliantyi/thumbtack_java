package net.thumbtack.school.windows.managers;

import net.thumbtack.school.windows.v4.Point;
import net.thumbtack.school.windows.v4.base.Window;
import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;

import java.util.Objects;

public class Manager<T extends Window> {
    private T window;

    public Manager(T window) throws WindowException {
        if (window == null) throw new WindowException(WindowErrorCode.NULL_WINDOW);
        this.window = window;
    }

    public T getWindow() {
        return window;
    }

    public void setWindow(T window) throws WindowException {
        if (window == null) throw new WindowException(WindowErrorCode.NULL_WINDOW);
        this.window = window;
    }

    public void moveTo(int x, int y) {
        window.moveTo(x, y);
    }

    public void moveTo(Point point) {
        window.moveTo(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager<?> manager = (Manager<?>) o;
        return window.equals(manager.window);
    }

    @Override
    public int hashCode() {
        return Objects.hash(window);
    }
}
