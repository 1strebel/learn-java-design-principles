package com.example.composite;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

public class GeometricShapes {

    static class GraphicObject
    {
        protected String name = "Group";

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String color;
        public List<GraphicObject> children = new ArrayList<>();

        private void print(StringBuilder stringBuilder,  int depth)
        {
            stringBuilder.append(String.join("", Collections.nCopies(depth, "*")))
                    .append(depth > 0 ? " " : "")
                    .append(isNullOrEmpty(color) ? "" : color + " ")
                    .append(getName())
                    .append(System.lineSeparator());
            for (GraphicObject child : children)
                child.print(stringBuilder,  depth+1);
        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            print(sb, 0);
            return sb.toString();
        }
    }

    static class Circle extends GraphicObject
    {
        public Circle(String color)
        {
            name = "Circle";
            this.color = color;
        }
    }

    static class Square extends GraphicObject
    {
        public Square(String color)
        {
            name = "Square";
            this.color = color;
        }
    }

    public static void main(String[] args)
    {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.children.add(new Square("Red"));
        drawing.children.add(new Circle("Yellow"));

        GraphicObject group = new GraphicObject();
        group.children.add(new Circle("Blue"));
        group.children.add(new Square("Blue"));
        drawing.children.add(group);

        System.out.println(drawing);
    }
}
