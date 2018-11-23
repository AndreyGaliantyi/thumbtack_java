package net.thumbtack.school.windows.v3.base;

import net.thumbtack.school.windows.v3.Desktop;
import net.thumbtack.school.windows.v3.Point;

import java.util.Objects;

public abstract class RectWindow extends Window {
    private Point topLeft;
    private Point bottomRight;

    public RectWindow(Point topLeft, Point bottomRight, boolean active) {
        super(active);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    public int getWidth() {
        return bottomRight.getX() - topLeft.getX() + 1;
    }

    public int getHeight() {
        return bottomRight.getY() - topLeft.getY() + 1;
    }

    public void moveTo(int x, int y) {
        int width = getWidth();
        int height = getHeight();
        topLeft.setX(x);
        topLeft.setY(y);
        bottomRight.setX(x + width - 1);
        bottomRight.setY(y + height - 1);
    }

    public void moveRel(int dx, int dy) {
        topLeft.setX(topLeft.getX() + dx);
        topLeft.setY(topLeft.getY() + dy);
        bottomRight.setX(bottomRight.getX() + dx);
        bottomRight.setY(bottomRight.getY() + dy);
    }

    public void resize(double ratio) {
        int newWidth, newHeight;
        if (getWidth() * ratio >= 1) newWidth = (int) (getWidth() * ratio);
        else newWidth = 1;
        if (getHeight() * ratio >= 1) newHeight = (int) (getHeight() * ratio);
        else newHeight = 1;
        bottomRight.setX(topLeft.getX() + newWidth - 1);
        bottomRight.setY(topLeft.getY() + newHeight - 1);
    }

    public boolean isInside(int x, int y) {
        if (topLeft.getX() > x || bottomRight.getX() < x) return false;
        return topLeft.getY() <= y && bottomRight.getY() >= y;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(RectWindow rectWindow) {
        Point topLeft = rectWindow.getTopLeft();
        Point bottomRight = rectWindow.getBottomRight();
        return !(getBottomRight().getX() < topLeft.getX() || bottomRight.getX() < getTopLeft().getX() ||
                bottomRight.getY() < getTopLeft().getY() || getBottomRight().getY() < topLeft.getY());
    }

    public boolean isInside(RectWindow rectWindow) {
        return isInside(rectWindow.getTopLeft()) && isInside(rectWindow.getBottomRight());
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return topLeft.isVisibleOnDesktop(desktop) && bottomRight.isVisibleOnDesktop(desktop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RectWindow that = (RectWindow) o;
        return Objects.equals(topLeft, that.topLeft) &&
                Objects.equals(bottomRight, that.bottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), topLeft, bottomRight);
    }
}
