package net.thumbtack.school.windows.managers;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.base.Window;
import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;

import java.util.Objects;

public class PairManager<T extends Window, T2 extends Window> {
    private T firstWindow;
    private T2 secondWindow;

    public PairManager(T firstWindow1, T2 secondWindow1) throws WindowException {
        if (firstWindow1 == null || secondWindow1 == null) throw new WindowException(WindowErrorCode.NULL_WINDOW);
        this.firstWindow = firstWindow1;
        this.secondWindow = secondWindow1;
    }

    public T getFirstWindow() {
        return firstWindow;
    }

    public T2 getSecondWindow() {
        return secondWindow;
    }

    public void setFirstWindow(T firstWindow) throws WindowException {
        if (firstWindow == null) throw new WindowException(WindowErrorCode.NULL_WINDOW);
        this.firstWindow = firstWindow;
    }

    public void setSecondWindow(T2 secondWindow) throws WindowException {
        if (secondWindow == null) throw new WindowException(WindowErrorCode.NULL_WINDOW);
        this.secondWindow = secondWindow;
    }

    public boolean allWindowsFullyVisibleOnDesktop(PairManager manager, Desktop desktop) {
        if (manager == null || desktop == null) return false;
        return firstWindow.isFullyVisibleOnDesktop(desktop) && secondWindow.isFullyVisibleOnDesktop(desktop) &&
                manager.getFirstWindow().isFullyVisibleOnDesktop(desktop) &&
                manager.getSecondWindow().isFullyVisibleOnDesktop(desktop);
    }

    public static boolean allWindowsFullyVisibleOnDesktop(PairManager firstMgr, PairManager secondMgr, Desktop desktop) {
        if (firstMgr == null || secondMgr == null || desktop == null) return false;
        return firstMgr.getFirstWindow().isFullyVisibleOnDesktop(desktop) &&
                firstMgr.getSecondWindow().isFullyVisibleOnDesktop(desktop) &&
                secondMgr.getFirstWindow().isFullyVisibleOnDesktop(desktop) &&
                secondMgr.getSecondWindow().isFullyVisibleOnDesktop(desktop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairManager<?, ?> that = (PairManager<?, ?>) o;
        return firstWindow.equals(that.firstWindow) &&
                secondWindow.equals(that.secondWindow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstWindow, secondWindow);
    }
}
