package com.svalero.TopLaptop.util;

import java.io.File;
import java.net.URL;

public class Resources {

    public static URL getUI(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
    }
}