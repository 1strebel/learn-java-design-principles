package com.example.decorator;
import java.util.function.Supplier;

public class StaticDecorator {

    interface Shape
    {
        String info();
    }

    static class Circle implements Shape
    {
        private float radius;

        public Circle(float radius)
        {
            this.radius = radius;
        }

        void resize(float factor)
        {
            radius *= factor;
        }

        @Override
        public String info()
        {
            return "A circle of radius " + radius;
        }
    }

    static class Square implements Shape
    {
        private float side;

        public Square(float side)
        {
            this.side = side;
        }

        @Override
        public String info()
        {
            return "A square with side " + side;
        }
    }

// we are NOT altering the base class of these objects
// cannot make ColoredSquare, ColoredCircle

    static class ColoredShape<T extends Shape> implements Shape {
        private Shape shape;
        private String color;

        public ColoredShape(Supplier<? extends T> ctor, String color) {
            shape = ctor.get();
            this.color = color;
        }

        @Override
        public String info() {
            return shape.info() + " has the color " + color;
        }
    }

    static class TransparentShape<T extends Shape> implements Shape
    {
        private Shape shape;
        private int transparency;

        public TransparentShape(Supplier<? extends T> ctor, int transparency)
        {
            shape = ctor.get();
            this.transparency = transparency;
        }

        @Override
        public String info() {
            return shape.info() + " has " + transparency + "% transparency";
        }
    }

    static class StaticDecoratorDemo {
        public static void main(String[] args) {
            Circle circle = new Circle(10);
            System.out.println(circle.info());

            ColoredShape<Square> blueSquare = new ColoredShape<>(() -> new Square(20), "blue");
            System.out.println(blueSquare.info());

            // ugly!
            TransparentShape<ColoredShape<Circle>> myCircle =
                    new TransparentShape<>(() ->
                            new ColoredShape<>(() -> new Circle(5), "green"), 50);
            System.out.println(myCircle.info());
            // cannot call myCircle.resize()

            ColoredShape<TransparentShape<Square>> mySquare =
                    new ColoredShape<>(() ->
                            new TransparentShape<>(() -> new Square(5), 12), "green");
            System.out.println(mySquare.info());
        }
    }
}
