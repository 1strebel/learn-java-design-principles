package com.example.bridge;

class VectorRenderer implements Renderer
{
    @Override
    public void renderCircle(float radius)
    {
        System.out.println("Drawing a circle of radius " + radius);
    }
}