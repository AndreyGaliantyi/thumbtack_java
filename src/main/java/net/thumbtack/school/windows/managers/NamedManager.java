package net.thumbtack.school.windows.managers;

import net.thumbtack.school.windows.v4.base.Window;
import net.thumbtack.school.windows.v4.base.WindowException;

import java.util.Objects;

public class NamedManager<T extends Window> extends Manager {
    private String name;

    public NamedManager(T window, String name) throws WindowException {
        super(window);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NamedManager<?> that = (NamedManager<?>) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
