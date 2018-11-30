package net.thumbtack.school.windows.managers;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.base.Window;
import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.cursors.Cursor;

import java.util.Arrays;

public class ArrayManager<T extends Window> {
    private T[] windows;

    public ArrayManager(T[] windows) throws WindowException {
        if (windows == null) throw new WindowException(WindowErrorCode.NULL_WINDOW);
        for (T window : windows) {
            if (window == null) throw new WindowException(WindowErrorCode.NULL_WINDOW);
        }
        this.windows = windows;
    }

    public T[] getWindows() {
        return windows;
    }

    public void setWindows(T[] windows) throws WindowException {
        if (windows == null) throw new WindowException(WindowErrorCode.NULL_WINDOW);
        for (T window : windows) {
            if (window == null) throw new WindowException(WindowErrorCode.NULL_WINDOW);
        }
        this.windows = windows;
    }

    public T getWindow(int index) throws WindowException {
        if (index < 0 || index >= windows.length) throw new WindowException(WindowErrorCode.WRONG_INDEX);
        return windows[index];
    }

    public void setWindow(T window, int index) throws WindowException {
        if (window == null) throw new WindowException(WindowErrorCode.NULL_WINDOW);
        if (index < 0 || index >= windows.length) throw new WindowException(WindowErrorCode.WRONG_INDEX);
        windows[index] = window;
    }

    public boolean isSameSize(ArrayManager manager) {
        if (manager == null) return false;
        return windows.length == manager.getWindows().length;
    }

    public boolean allWindowsFullyVisibleOnDesktop(Desktop desktop) {
        if (desktop == null) return false;
        for (T window : windows) {
            if (!window.isFullyVisibleOnDesktop(desktop)) return false;
        }
        return true;
    }

    public boolean anyWindowFullyVisibleOnDesktop(Desktop desktop) {
        if (desktop == null) return false;
        for (T window : windows) {
            if (window.isFullyVisibleOnDesktop(desktop)) return true;
        }
        return false;
    }

    public Window getFirstWindowFromCursor(Cursor cursor) {
        if (cursor == null) return null;
        for (int i = 0; i < windows.length; i++) {
            if (windows[i].isInside(cursor)) return windows[i];
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayManager<?> that = (ArrayManager<?>) o;
        return Arrays.equals(windows, that.windows);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(windows);
    }
}
