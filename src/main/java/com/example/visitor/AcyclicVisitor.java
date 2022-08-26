package com.example.visitor;

// really creepy implementation of acyclic visitor
interface VisitorA {
}

interface ExpressionVisitorA extends VisitorA {
    void visit(ExpressionA obj);
}

interface DoubleExpressionVisitorA extends VisitorA {
    void visit(DoubleExpressionA obj);
}

interface AdditionExpressionVisitorA extends VisitorA {
    void visit(AdditionExpressionA obj);
}

abstract class ExpressionA {
    // optional
    public void accept(VisitorA visitor) {
        if (visitor instanceof ExpressionVisitorA) {
            ((ExpressionVisitorA) visitor).visit(this);
        }
    }
}

class DoubleExpressionA extends ExpressionA {
    public double value;

    public DoubleExpressionA(double value) {
        this.value = value;
    }

    @Override
    public void accept(VisitorA visitor) {
        if (visitor instanceof DoubleExpressionVisitorA) {
            ((DoubleExpressionVisitorA) visitor).visit(this);
        }
    }
}

class AdditionExpressionA extends ExpressionA {
    public ExpressionA left, right;

    public AdditionExpressionA(ExpressionA left, ExpressionA right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(VisitorA visitor) {
        if (visitor instanceof AdditionExpressionVisitorA) {
            ((AdditionExpressionVisitorA) visitor).visit(this);
        }
    }
}

class ExpressionPrinterA implements
        DoubleExpressionVisitorA,
        AdditionExpressionVisitorA {
    private StringBuilder sb = new StringBuilder();

    @Override
    public void visit(DoubleExpressionA obj) {
        sb.append(obj.value);
    }

    @Override
    public void visit(AdditionExpressionA obj) {
        sb.append('(');
        obj.left.accept(this);
        sb.append('+');
        obj.right.accept(this);
        sb.append(')');
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

class AcyclicVisitorDemo {
    public static void main(String[] args) {
        AdditionExpressionA e = new AdditionExpressionA(
                new DoubleExpressionA(1),
                new AdditionExpressionA(
                        new DoubleExpressionA(2),
                        new DoubleExpressionA(3)
                )
        );
        ExpressionPrinterA ep = new ExpressionPrinterA();
        ep.visit(e);
        System.out.println(ep);
    }
}
