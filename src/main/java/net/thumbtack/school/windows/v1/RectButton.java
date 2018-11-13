package net.thumbtack.school.windows.v1;

import java.util.Objects;

public class RectButton {
    private boolean active;
    private Point topLeft;
    private Point bottomRight;

    public RectButton(Point topLeft, Point bottomRight, boolean active){
        this.topLeft=topLeft;
        this.bottomRight=bottomRight;
        this.active=active;
    }

    public RectButton(int xLeft, int yTop, int width, int height, boolean active){
        topLeft=new Point(xLeft,yTop);
        bottomRight=new Point(xLeft+width-1,yTop+height-1);
        this.active=active;
    }

    public RectButton(Point topLeft, Point bottomRight){
        this.topLeft=topLeft;
        this.bottomRight=bottomRight;
        active=true;
    }

    public RectButton(int xLeft, int yTop, int width, int height){
        topLeft=new Point(xLeft,yTop);
        bottomRight=new Point(xLeft+width-1,yTop+height-1);
        active=true;
    }

    public Point getTopLeft(){
        return topLeft;
    }

    public Point getBottomRight(){
        return bottomRight;
    }

    public boolean isActive(){
        return active;
    }

    public void setTopLeft(Point topLeft){
        this.topLeft=topLeft;
    }

    public void setBottomRight(Point bottomRight){
        this.bottomRight=bottomRight;
    }

    public void setActive(boolean active){
        this.active=active;
    }

    public int getWidth(){
        return bottomRight.getX()-topLeft.getX()+1;
    }

    public int getHeight(){
        return bottomRight.getY()-topLeft.getY()+1;
    }

    public void moveTo(int x, int y){
        int width=getWidth();
        int height=getHeight();
        topLeft.setX(x);
        topLeft.setY(y);
        bottomRight.setX(x+width-1);
        bottomRight.setY(y+height-1);
    }

    public void moveTo(Point point){
        int width=getWidth();
        int height=getHeight();
        topLeft.setX(point.getX());
        topLeft.setY(point.getY());
        bottomRight.setX(point.getX()+width-1);
        bottomRight.setY(point.getY()+height-1);
    }

    public void moveRel(int dx, int dy){
        topLeft.setX(topLeft.getX()+dx);
        topLeft.setY(topLeft.getY()+dy);
        bottomRight.setX(bottomRight.getX()+dx);
        bottomRight.setY(bottomRight.getY()+dy);
    }

    public void resize(double ratio){
        int newWidth,newHeight;
        if (getWidth()*ratio>=1) newWidth= (int) (getWidth()*ratio);
        else newWidth=1;
        if (getHeight()*ratio>=1) newHeight= (int) (getHeight()*ratio);
        else newHeight=1;
        bottomRight.setX(topLeft.getX()+newWidth-1);
        bottomRight.setY(topLeft.getY()+newHeight-1);
    }

    public boolean isInside(int x, int y){
        if (topLeft.getX()>x || bottomRight.getX()<x) return false;
        return topLeft.getY()<=y && bottomRight.getY()>=y;
    }

    public boolean isInside(Point point){
        int x=point.getX();
        int y=point.getY();
        if (topLeft.getX()>x || bottomRight.getX()<x) return false;
        return topLeft.getY()<=y && bottomRight.getY()>=y;
    }

    public boolean isIntersects(RectButton rectButton){
        Point topLeft=rectButton.getTopLeft();
        Point bottomRight=rectButton.getBottomRight();
        return !(this.bottomRight.getX()<topLeft.getX() || bottomRight.getX()<this.topLeft.getX() ||
                bottomRight.getY()<this.topLeft.getY() || this.bottomRight.getY()<topLeft.getY());
    }

    public boolean isInside(RectButton rectButton){
        return isInside(rectButton.topLeft) && isInside(rectButton.bottomRight);
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop){
        return topLeft.isVisibleOnDesktop(desktop) && bottomRight.isVisibleOnDesktop(desktop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RectButton that = (RectButton) o;
        return active == that.active &&
                Objects.equals(topLeft, that.topLeft) &&
                Objects.equals(bottomRight, that.bottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, topLeft, bottomRight);
    }
}
