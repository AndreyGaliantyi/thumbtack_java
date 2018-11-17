package net.thumbtack.school.windows.v2;

import net.thumbtack.school.base.StringOperations;

import java.util.Arrays;
import java.util.Objects;

public class ListBox {
    private boolean active;
    private Point topLeft;
    private Point bottomRight;
    private String[] lines;

    public ListBox(Point topLeft, Point bottomRight, boolean active, String[] lines){
        this.topLeft=topLeft;
        this.bottomRight=bottomRight;
        this.active=active;
        if (lines==null)return;
        int length = lines.length;
        this.lines=new String[length];
        System.arraycopy(lines,0,this.lines,0, length);
    }

    public ListBox(int xLeft, int yTop, int width, int height, boolean active, String[] lines){
        topLeft=new Point(xLeft,yTop);
        bottomRight=new Point(xLeft+width-1,yTop+height-1);
        this.active=active;
        if (lines==null)return;
        int length = lines.length;
        this.lines=new String[length];
        System.arraycopy(lines,0,this.lines,0, length);
    }

    public ListBox(Point topLeft, Point bottomRight,  String[] lines){
        this.topLeft=topLeft;
        this.bottomRight=bottomRight;
        active=true;
        if (lines==null)return;
        int length = lines.length;
        this.lines=new String[length];
        System.arraycopy(lines,0,this.lines,0, length);
    }

    public ListBox(int xLeft, int yTop, int width, int height, String[] lines){
        topLeft=new Point(xLeft,yTop);
        bottomRight=new Point(xLeft+width-1,yTop+height-1);
        active=true;
        if (lines==null)return;
        int length = lines.length;
        this.lines=new String[length];
        System.arraycopy(lines,0,this.lines,0, length);
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

    public String[] getLines(){
        return lines;
    }

    public void setLines(String[] lines){
        if (lines==null){this.lines=null; return;}
        int length = lines.length;
        this.lines=new String[length];
        System.arraycopy(lines,0,this.lines,0, length);
    }

    public String[] getLinesSlice(int from, int to){
        if (lines==null)return null;
        int length = lines.length;
        if (length -1<to){
            String[] linesSlice=new String[length-from];
            System.arraycopy(this.lines,from,linesSlice,0, length-from);
            return linesSlice;
        }else {
            String[] linesSlice=new String[to-from];
            System.arraycopy(this.lines,from,linesSlice,0, to-from);
            return linesSlice;
        }
    }

    public String getLine(int index){
        if (lines==null || index>lines.length-1)return null;
        return lines[index];
    }

    public void setLine(int index, String line){
        if (lines==null || index>lines.length-1)return;
        lines[index]=line;
    }

    public Integer findLine(String line){
        if (lines==null)return null;
        for (int i=0;i<lines.length;i++){
            if (line.equals(lines[i]))return i;
        }
        return null;
    }

    public void reverseLineOrder(){
        if (lines==null)return;
        int length = lines.length;
        if (length<2)return;
        String s;
        for (int i=0;i<length/2;i++) {
            s=lines[i];
            lines[i]=lines[length-i-1];
            lines[length-i-1]=s;
        }
    }

    public void reverseLines(){
        if (lines==null)return;
        int i=0;
        for (String s:lines) {
            lines[i]=StringOperations.reverse(s);
            i++;
        }
    }

    public void duplicateLines(){
        if (lines==null)return;
        String[] newLines=new String[lines.length*2];
        for (int i=0,index=0;i<lines.length;i++,index+=2) {
            newLines[index]=lines[i];
            newLines[index+1]=lines[i];
        }
        lines=newLines;
    }

    public void removeOddLines(){
        if (lines==null)return;
        int length = lines.length;
        if (length ==1)return;
        String[] newLines;
        if (length %2==0)newLines=new String[length /2];
        else newLines=new String[length /2+1];
        for (int i = 0,index=0; i< length; i+=2,index++) {
            newLines[index]=lines[i];
        }
        lines=newLines;
    }

    public boolean isSortedDescendant(){
        if (lines==null)return true;
        for (int i=1;i<lines.length;i++){
            if (!StringOperations.isLess(lines[i],lines[i-1]))return false;
        }
        return true;
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
        topLeft=point;
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

    public boolean isIntersects(ListBox listBox){
        Point topLeft=listBox.getTopLeft();
        Point bottomRight=listBox.getBottomRight();
        return !(this.bottomRight.getX()<topLeft.getX() || bottomRight.getX()<this.topLeft.getX() ||
                bottomRight.getY()<this.topLeft.getY() || this.bottomRight.getY()<topLeft.getY());
    }

    public boolean isInside(ListBox listBox){
        return isInside(listBox.topLeft) && isInside(listBox.bottomRight);
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop){
        return topLeft.isVisibleOnDesktop(desktop) && bottomRight.isVisibleOnDesktop(desktop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListBox listBox = (ListBox) o;
        return active == listBox.active &&
                Objects.equals(topLeft, listBox.topLeft) &&
                Objects.equals(bottomRight, listBox.bottomRight) &&
                Arrays.equals(lines, listBox.lines);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(active, topLeft, bottomRight);
        result = 31 * result + Arrays.hashCode(lines);
        return result;
    }
}
