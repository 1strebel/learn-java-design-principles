package com.example.visitor;

interface ExpressionVisitor {
    void visit(DoubleExpressionC e);

    void visit(AdditionExpressionC e);
}

abstract class ExpressionC {
    public abstract void accept(ExpressionVisitor visitor);
}

class DoubleExpressionC extends ExpressionC {
    public double value;

    public DoubleExpressionC(double value) {
        this.value = value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}

class AdditionExpressionC extends ExpressionC {
    public ExpressionC left, right;

    public AdditionExpressionC(ExpressionC left, ExpressionC right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}

// separation of concerns
class ExpressionPrinterC implements ExpressionVisitor {
    private StringBuilder sb = new StringBuilder();

    @Override
    public void visit(DoubleExpressionC e) {
        sb.append(e.value);
    }

    @Override
    public void visit(AdditionExpressionC e) {
        sb.append("(");
        e.left.accept(this);
        sb.append("+");
        e.right.accept(this);
        sb.append(")");
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

class ExpressionCalculator implements ExpressionVisitor {
    public double result;

    @Override
    public void visit(DoubleExpressionC e) {
        result = e.value;
    }

    @Override
    public void visit(AdditionExpressionC e) // this is a test too
    {
        e.left.accept(this);
        double a = result;
        e.right.accept(this);
        double b = result;
        result = a + b; // this is a test
    }
}

class ClassicVisitorDemo {
    public static void main(String[] args) {
        // 1+(2+3)
        AdditionExpressionC e = new AdditionExpressionC(
                new DoubleExpressionC(1),
                new AdditionExpressionC(
                        new DoubleExpressionC(2),
                        new DoubleExpressionC(3)
                ));
        ExpressionPrinterC ep = new ExpressionPrinterC();
        ep.visit(e);
        System.out.println(ep);

        ExpressionCalculator calc = new ExpressionCalculator();
        calc.visit(e);
        System.out.println(ep + " = " + calc.result);
    }
}
