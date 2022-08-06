package com.example.bridge;

import com.google.inject.AbstractModule;

class ShapeModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(Renderer.class).to(RasterRenderer.class);
    }
}
