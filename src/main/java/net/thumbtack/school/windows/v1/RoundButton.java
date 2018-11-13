package net.thumbtack.school.windows.v1;

import java.util.Objects;

public class RoundButton {
    private Point center;
    private int radius;
    private boolean active;

    public RoundButton(Point center, int radius, boolean active){
        this.center=center;
        this.radius=radius;
        this.active=active;
    }

    public RoundButton(int xCenter, int yCenter, int radius, boolean active){
        center=new Point(xCenter,yCenter);
        this.radius=radius;
        this.active=active;
    }

    public RoundButton(Point center, int radius){
        this.center=center;
        this.radius=radius;
        active=true;
    }

    public RoundButton(int xCenter, int yCenter, int radius){
        center=new Point(xCenter,yCenter);
        this.radius=radius;
        active=true;
    }

    public Point getCenter(){
        return center;
    }

    public int getRadius(){
        return radius;
    }

    public boolean isActive(){
        return active;
    }

    public void moveTo(int x, int y){
        center.setX(x);
        center.setY(y);
    }

    public void moveTo(Point point){
        center=point;
    }

    public void setCenter(int x, int y){
        center=new Point(x,y);
    }

    public void setRadius(int radius){
        this.radius=radius;
    }

    public void setActive(boolean active){
        this.active=active;
    }

    public void moveRel(int dx, int dy){
        moveTo(center.getX()+dx,center.getY()+dy);
    }

    public void resize(double ratio){
        int newRadius= (int) (radius*ratio);
        if (newRadius<1) {
            radius=1;
            return;
        }
        radius=newRadius;
    }

    public boolean isInside(int x, int y){
        int k1=Math.abs(x - center.getX());
        int k2=Math.abs(y - center.getY());
        double gip=Math.sqrt(Math.pow(k1,2)+Math.pow(k2,2));
        if (gip>radius)return false;
        else return true;
    }

    public boolean isInside(Point point){
        int k1=Math.abs(point.getX() - center.getX());
        int k2=Math.abs(point.getY() - center.getY());
        double gip=Math.sqrt(Math.pow(k1,2)+Math.pow(k2,2));
        if (gip>radius)return false;
        else return true;
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop){
        Point top=new Point(center.getX(),center.getY()-radius);
        Point bottom=new Point(center.getX(),center.getY()+radius);
        Point left=new Point(center.getX()-radius,center.getY());
        Point right=new Point(center.getX()+radius,center.getY());
        return top.isVisibleOnDesktop(desktop) && bottom.isVisibleOnDesktop(desktop)
                && left.isVisibleOnDesktop(desktop) && right.isVisibleOnDesktop(desktop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundButton that = (RoundButton) o;
        return radius == that.radius &&
                active == that.active &&
                Objects.equals(center, that.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius, active);
    }
}
