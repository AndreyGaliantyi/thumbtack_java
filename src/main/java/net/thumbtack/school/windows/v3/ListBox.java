package net.thumbtack.school.windows.v3;

import net.thumbtack.school.base.StringOperations;
import net.thumbtack.school.windows.v3.base.RectWindow;

import java.util.Arrays;

public class ListBox extends RectWindow {
    private String[] lines;

    public ListBox(Point topLeft, Point bottomRight, boolean active, String[] lines) {
        super(topLeft, bottomRight, active);
        if (lines == null) return;
        int length = lines.length;
        this.lines = new String[length];
        System.arraycopy(lines, 0, this.lines, 0, length);
    }

    public ListBox(int xLeft, int yTop, int width, int height, boolean active, String[] lines) {
        super(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), active);
        if (lines == null) return;
        int length = lines.length;
        this.lines = new String[length];
        System.arraycopy(lines, 0, this.lines, 0, length);
    }

    public ListBox(Point topLeft, Point bottomRight, String[] lines) {
        super(topLeft, bottomRight, true);
        if (lines == null) return;
        int length = lines.length;
        this.lines = new String[length];
        System.arraycopy(lines, 0, this.lines, 0, length);
    }

    public ListBox(int xLeft, int yTop, int width, int height, String[] lines) {
        super(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), true);
        if (lines == null) return;
        int length = lines.length;
        this.lines = new String[length];
        System.arraycopy(lines, 0, this.lines, 0, length);
    }

    public String[] getLines() {
        return lines;
    }

    public void setLines(String[] lines) {
        if (lines == null) {
            this.lines = null;
            return;
        }
        int length = lines.length;
        this.lines = new String[length];
        System.arraycopy(lines, 0, this.lines, 0, length);
    }

    public String[] getLinesSlice(int from, int to) {
        if (lines == null) return null;
        int length = lines.length;
        if (length - 1 < to) {
            String[] linesSlice = new String[length - from];
            System.arraycopy(this.lines, from, linesSlice, 0, length - from);
            return linesSlice;
        } else {
            String[] linesSlice = new String[to - from];
            System.arraycopy(this.lines, from, linesSlice, 0, to - from);
            return linesSlice;
        }
    }

    public String getLine(int index) {
        if (lines == null || index > lines.length - 1) return null;
        return lines[index];
    }

    public void setLine(int index, String line) {
        if (lines == null || index > lines.length - 1) return;
        lines[index] = line;
    }

    public Integer findLine(String line) {
        if (lines == null) return null;
        for (int i = 0; i < lines.length; i++) {
            if (line.equals(lines[i])) return i;
        }
        return null;
    }

    public void reverseLineOrder() {
        if (lines == null) return;
        int length = lines.length;
        if (length < 2) return;
        String s;
        for (int i = 0; i < length / 2; i++) {
            s = lines[i];
            lines[i] = lines[length - i - 1];
            lines[length - i - 1] = s;
        }
    }

    public void reverseLines() {
        if (lines == null) return;
        int i = 0;
        for (String s : lines) {
            lines[i] = StringOperations.reverse(s);
            i++;
        }
    }

    public void duplicateLines() {
        if (lines == null) return;
        String[] newLines = new String[lines.length * 2];
        for (int i = 0, index = 0; i < lines.length; i++, index += 2) {
            newLines[index] = lines[i];
            newLines[index + 1] = lines[i];
        }
        lines = newLines;
    }

    public void removeOddLines() {
        if (lines == null) return;
        int length = lines.length;
        if (length == 1) return;
        String[] newLines;
        if (length % 2 == 0) newLines = new String[length / 2];
        else newLines = new String[length / 2 + 1];
        for (int i = 0, index = 0; i < length; i += 2, index++) {
            newLines[index] = lines[i];
        }
        lines = newLines;
    }

    public boolean isSortedDescendant() {
        if (lines == null) return true;
        for (int i = 1; i < lines.length; i++) {
            if (!StringOperations.isLess(lines[i], lines[i - 1])) return false;
        }
        return true;
    }

    public boolean isIntersects(ListBox listBox) {
        Point topLeft = listBox.getTopLeft();
        Point bottomRight = listBox.getBottomRight();
        return !(getBottomRight().getX() < topLeft.getX() || bottomRight.getX() < getTopLeft().getX() ||
                bottomRight.getY() < getTopLeft().getY() || getBottomRight().getY() < topLeft.getY());
    }

    public boolean isInside(ListBox listBox) {
        return isInside(listBox.getTopLeft()) && isInside(listBox.getBottomRight());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ListBox listBox = (ListBox) o;
        return Arrays.equals(lines, listBox.lines);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(lines);
        return result;
    }
}
