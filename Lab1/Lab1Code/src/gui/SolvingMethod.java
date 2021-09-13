package gui;

public abstract class SolvingMethod {

     double accuracy;
     double leftBorder;
     double rightBorder;
     Function function;
     double currentX;
     int steps = 0;

    public  String solve() {
        return "solving...";
    }

    public boolean checkSuffCondition(double a, double b){
        if(function.getFunc().solve(a, 0)*function.getFunc().solve(b,0) > 0) return false;
        else return true;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(double leftBorder) {
        this.leftBorder = leftBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(double rightBorder) {
        this.rightBorder = rightBorder;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }


    public double getCurrentX() {
        return currentX;
    }

    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }


    public void initParams(double leftBorder, double rightBorder, double accuracy, Function function){
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.accuracy = accuracy;
        this.function = function;
    }
}
