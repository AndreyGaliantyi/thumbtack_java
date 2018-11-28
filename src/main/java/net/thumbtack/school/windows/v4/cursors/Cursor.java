package net.thumbtack.school.windows.v4.cursors;

import net.thumbtack.school.windows.v4.Point;

import java.util.Objects;

public class Cursor extends Point {
    private CursorForm cursorForm;

    public Cursor(int x, int y, CursorForm cursorForm) {
        super(x, y);
        this.cursorForm = cursorForm;
    }

    public Cursor(Point point, CursorForm cursorForm) {
        super(point);
        this.cursorForm = cursorForm;
    }

    public CursorForm getCursorForm() {
        return cursorForm;
    }

    public void setCursorForm(CursorForm cursorForm) {
        this.cursorForm = cursorForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cursor cursor = (Cursor) o;
        return cursorForm == cursor.cursorForm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cursorForm);
    }
}
