package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.iface.Movable;

import java.util.Objects;

public class Point implements Movable {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveRel(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public boolean isVisibleOnDesktop(Desktop desktop) {
        if (x >= desktop.getWidth() || x < 0) return false;
        return y < desktop.getHeight() && y >= 0;
    }

    public boolean isNotVisibleOnDesktop(Desktop desktop) {
        return x >= desktop.getWidth() || y >= desktop.getHeight() || x < 0 || y < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
