package net.thumbtack.school.windows.v4.base;

public enum WindowErrorCode {
    WRONG_STATE, WRONG_INDEX, EMPTY_ARRAY;

    private String errorString;

    private void WndowErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
