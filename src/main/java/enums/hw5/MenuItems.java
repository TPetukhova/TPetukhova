package enums.hw5;

public enum MenuItems {

    SUPPORT("Support"), DATES("Dates"), COMPLEX_TABLE("Complex Table"), SIMPLE_TABLE("Simple Table"),
    USER_TABLE("User Table"),TABLE_WITH_PAGES("Table with pages"),
    DIFFERENT_ELEMENTS("Different elements"), PERFORMANCE("Performance");

    private final String value;

    MenuItems(String value) {
        this.value = value;
    }

    public String toString(){
        return value;
    }
}
