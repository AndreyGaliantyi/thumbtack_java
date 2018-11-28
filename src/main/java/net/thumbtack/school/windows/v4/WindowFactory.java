package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

public class WindowFactory {
    private static int rectButtonCount = 0;
    private static int roundButtonCount = 0;

    public static RectButton createRectButton(Point leftTop, Point rightBottom, WindowState state, String text) throws WindowException {
        rectButtonCount++;
        return new RectButton(leftTop, rightBottom, state, text);
    }

    public static RoundButton createRoundButton(Point center, int radius, WindowState state, String text) throws WindowException {
        roundButtonCount++;
        return new RoundButton(center, radius, state, text);
    }

    public static int getRectButtonCount() {
        return rectButtonCount;
    }

    public static int getRoundButtonCount() {
        return roundButtonCount;
    }

    public static int getWindowCount() {
        return rectButtonCount + roundButtonCount;
    }

    public static void reset() {
        rectButtonCount = 0;
        roundButtonCount = 0;
    }
}