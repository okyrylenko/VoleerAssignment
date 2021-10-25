package com.voleer.test.ui.core;

import com.google.inject.AbstractModule;;


public class GuiceDI extends AbstractModule {

    @Override
    protected void configure() {
        bind(IBrowser.class).to(Browser.class);

    }
}

