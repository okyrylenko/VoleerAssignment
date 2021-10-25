package com.voleer.test.ui.core;

import java.lang.reflect.InvocationTargetException;

public interface IBrowser {
    <T extends  Navigatable> T navigateTo(Class<T> c) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;


    String getUrl();

    boolean isCookieExist(String name);
}
