package net.thumbtack.school.windows.v3;

import net.thumbtack.school.windows.v3.base.RoundWindow;

public class RoundButton extends RoundWindow {

    public RoundButton(Point center, int radius, boolean active, String text) {
        super(center, radius, active, text);
    }

    public RoundButton(int xCenter, int yCenter, int radius, boolean active, String text) {
        super(new Point(xCenter, yCenter), radius, active, text);
    }

    public RoundButton(Point center, int radius, String text) {
        super(center, radius, true, text);
    }

    public RoundButton(int xCenter, int yCenter, int radius, String text) {
        super(new Point(xCenter, yCenter), radius, true, text);
    }

}
