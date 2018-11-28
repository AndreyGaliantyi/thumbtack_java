package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

import java.util.Objects;

public class ComboBox extends ListBox {
    private Integer selected;

    public ComboBox(Point topLeft, Point bottomRight, WindowState state, String[] lines, Integer selected) throws WindowException {
        super(topLeft, bottomRight, state, lines);
        if (lines != null && selected != null && (lines.length - 1 < selected || selected < 0))
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        if (lines == null && selected != null) throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        this.selected = selected;
    }

    public ComboBox(Point topLeft, Point bottomRight, String state, String[] lines, Integer selected) throws WindowException {
        super(topLeft, bottomRight, state, lines);
        if (lines != null && selected != null && (lines.length - 1 < selected || selected < 0))
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        if (lines == null && selected != null) throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        this.selected = selected;
    }

    public ComboBox(int xLeft, int yTop, int width, int height, WindowState state, String[] lines, Integer selected) throws WindowException {
        super(xLeft, yTop, width, height, state, lines);
        if (lines != null && selected != null && (lines.length - 1 < selected || selected < 0))
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        if (lines == null && selected != null) throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        this.selected = selected;
    }

    public ComboBox(int xLeft, int yTop, int width, int height, String state, String[] lines, Integer selected) throws WindowException {
        super(xLeft, yTop, width, height, state, lines);
        if (lines != null && selected != null && (lines.length - 1 < selected || selected < 0))
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        if (lines == null && selected != null) throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        this.selected = selected;
    }

    public ComboBox(Point topLeft, Point bottomRight, String[] lines, Integer selected) throws WindowException {
        super(topLeft, bottomRight, lines);
        if (lines != null && selected != null && (lines.length - 1 < selected || selected < 0))
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        if (lines == null && selected != null) throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        this.selected = selected;
    }

    public ComboBox(int xLeft, int yTop, int width, int height, String[] lines, Integer selected) throws WindowException {
        super(xLeft, yTop, width, height, lines);
        if (lines != null && selected != null && (lines.length - 1 < selected || selected < 0))
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        if (lines == null && selected != null) throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        this.selected = selected;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) throws WindowException {
        if (getLines() == null && selected != null) throw new WindowException(WindowErrorCode.EMPTY_ARRAY);
        if (getLines() != null && selected != null && (getLines().length - 1 < selected || selected < 0))
            throw new WindowException(WindowErrorCode.WRONG_INDEX);
        this.selected = selected;
    }

    @Override
    public void setLines(String[] lines) {
        super.setLines(lines);
        selected = null;
    }

    public boolean isIntersects(ComboBox comboBox) {
        return super.isIntersects(comboBox);
    }

    public boolean isInside(ComboBox comboBox) {
        return super.isInside(comboBox);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComboBox comboBox = (ComboBox) o;
        return Objects.equals(selected, comboBox.selected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), selected);
    }
}
