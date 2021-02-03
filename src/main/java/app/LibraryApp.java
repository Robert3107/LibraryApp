package app;

public class LibraryApp {

    private static final String APP_NAME = "Biblioteka";

    public static void main(String[] args) {
        startPrint();
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.controlApp();
    }

    public static void startPrint() {
        System.out.println("----------| " + APP_NAME + " |----------" + "\n");
    }
}
