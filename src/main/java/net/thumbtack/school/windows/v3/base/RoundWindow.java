package net.thumbtack.school.windows.v3.base;

import net.thumbtack.school.windows.v3.Desktop;
import net.thumbtack.school.windows.v3.Point;

import java.util.Objects;

public abstract class RoundWindow extends Window {
    private Point center;
    private int radius;
    private String text;

    public RoundWindow(Point center, int radius, boolean active, String text) {
        super(active);
        this.center = center;
        this.radius = radius;
        this.text = text;
    }

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public void setCenter(int x, int y) {
        center = new Point(x, y);
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void moveTo(int x, int y) {
        center.setX(x);
        center.setY(y);
    }

    public void moveRel(int dx, int dy) {
        moveTo(center.getX() + dx, center.getY() + dy);
    }

    public void resize(double ratio) {
        int newRadius = (int) (radius * ratio);
        if (newRadius < 1) {
            radius = 1;
            return;
        }
        radius = newRadius;
    }

    public boolean isInside(int x, int y) {
        int k1 = Math.abs(x - center.getX());
        int k2 = Math.abs(y - center.getY());
        double gip = Math.sqrt(Math.pow(k1, 2) + Math.pow(k2, 2));
        return (gip <= radius);
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        Point top = new Point(center.getX(), center.getY() - radius);
        Point bottom = new Point(center.getX(), center.getY() + radius);
        Point left = new Point(center.getX() - radius, center.getY());
        Point right = new Point(center.getX() + radius, center.getY());
        return top.isVisibleOnDesktop(desktop) && bottom.isVisibleOnDesktop(desktop)
                && left.isVisibleOnDesktop(desktop) && right.isVisibleOnDesktop(desktop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoundWindow that = (RoundWindow) o;
        return radius == that.radius &&
                Objects.equals(center, that.center) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), center, radius, text);
    }
}
