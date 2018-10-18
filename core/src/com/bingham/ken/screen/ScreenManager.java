package com.bingham.ken.screen;

/**
 * Created by ken on 8/27/14.
 */
public class ScreenManager  {

    private static Screen currentScreen;

    public static void setScreen(Screen screen) {
        if (currentScreen != null)
            currentScreen.dispose();
        currentScreen = screen;
        currentScreen.create();
    }

    public static Screen getCurrentScreen() {
        return currentScreen;
    }

}

