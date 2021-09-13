package gui;


public class NewtonMethod  extends SolvingMethod{

    double currentX, currentY, previousX, previousY;
    int iterCount = 0;
    Function firstFunc, secondFunc;
    double hlpX, hlpY;
    double[][] jacobiMatrix = new double[2][2];

    public NewtonMethod(Function firstFunc, Function secondFunc, double x, double y, double accuracy){
        this.firstFunc = firstFunc;
        this.secondFunc = secondFunc;
        this.currentX = x;
        this.currentY = y;
        this.accuracy = accuracy;
    }

    @Override
    public String solve() {
        while (firstFunc.getFunc().solve(currentX, currentY) > accuracy || secondFunc.getFunc().solve(currentX, currentY) > accuracy){
            iterCount++;
            previousX = currentX;
            previousY = currentY;
            initMatrix(previousX, previousY);

            currentX = previousX - hlpX;
            currentY = previousY - hlpY;
 
            if (iterCount >= 1000000)
                return "превышен лимит итераций";
        }

        return "X =  " + String.format("%4.10f ",currentX) + "   Y =   " + currentY;
    }


    //ДУБЛИРУЮЩИЙСЯ КОД
    private void initMatrix(double x, double y){

        initJacobi(firstFunc.getFunctionText(), 1, x);
        initJacobi(secondFunc.getFunctionText(),2, x);

        double det = jacobiMatrix[0][0] * jacobiMatrix[1][1] - jacobiMatrix[0][1] * jacobiMatrix[1][0];

        hlpX = (firstFunc.getFunc().solve(x,y)* jacobiMatrix[1][1] - secondFunc.getFunc().solve(x,y) * jacobiMatrix[0][1])/ det;
        hlpY = (jacobiMatrix[0][0] * secondFunc.getFunc().solve(x,y) - jacobiMatrix[1][0] * firstFunc.getFunc().solve(x,y))/ det;

    }

    // Выделенный из initMatrix метод
    private void initJacobi(String functionStr, int funcNumber, double x) {
        switch (functionStr){
            case "y - x^3 - 4 = 0":
                jacobiMatrix[funcNumber-1][0] = -3.0 * x * x;
                jacobiMatrix[funcNumber-1][1] = 1.0;
                break;
            case  "y + e^x + 1 = 0":
                jacobiMatrix[funcNumber-1][0] = Math.exp(x);
                jacobiMatrix[funcNumber-1][1] = 1.0;
                break;
            case "x^2 + y = 0":
                jacobiMatrix[funcNumber-1][0] = 2.0 * x;
                jacobiMatrix[funcNumber-1][1] = 1.0;
                break;
        }
    }


    public Function getFirstFunc() {
        return firstFunc;
    }

    public void setFirstFunc(Function firstFunc) {
        this.firstFunc = firstFunc;
    }

    public Function getSecondFunc() {
        return secondFunc;
    }
}
