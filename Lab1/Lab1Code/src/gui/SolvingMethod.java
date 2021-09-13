package gui;

public abstract class SolvingMethod {

     double accuracy;
     double leftBorder;
     double rightBorder;
     Functions function = null;
     double currentX;
     int steps = 0;
     String functionStr;

    public  String solve() {
        return "solving...";
    }

    public boolean checkSuffCondition(double a, double b){
        if(function.solve(a, 0)*function.solve(b,0) > 0) return false;
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

    public Functions getFunction() {
        return function;
    }

    public void setFunction(Functions function) {
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

    public void setfunсtionStr(String funсtionStr) {
        this.functionStr = funсtionStr;
    }

    public void initParams(double leftBorder, double rightBorder, double accuracy, Functions function, String funсtionStr){
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.accuracy = accuracy;
        this.function = function;
        this.functionStr = funсtionStr;
    }
}
