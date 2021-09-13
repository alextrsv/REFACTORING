package gui;


public class NewtonMethod  extends SolvingMethod{

    double currentX, currentY, previousX, previousY;
    int iterCount = 0;
    Functions firstFunc, secondFunc;
    String firstFuncStr, secondFuncStr;
    double hlpX, hlpY;
    double[][] jacobiMatrix = new double[2][2];

    public NewtonMethod(Functions firstFunc, Functions secondFunc, double x, double y, double accuracy){
        this.firstFunc = firstFunc;
        this.secondFunc = secondFunc;
        this.currentX = x;
        this.currentY = y;
        this.accuracy = accuracy;
    }

    @Override
    public String solve() {
        while (firstFunc.solve(currentX, currentY) > accuracy || secondFunc.solve(currentX, currentY) > accuracy){
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

        initJacobi(firstFuncStr, 1, x);
        initJacobi(secondFuncStr,2, x);

        double det = jacobiMatrix[0][0] * jacobiMatrix[1][1] - jacobiMatrix[0][1] * jacobiMatrix[1][0];

        hlpX = (firstFunc.solve(x,y)* jacobiMatrix[1][1] - secondFunc.solve(x,y) * jacobiMatrix[0][1])/ det;
        hlpY = (jacobiMatrix[0][0] * secondFunc.solve(x,y) - jacobiMatrix[1][0] * firstFunc.solve(x,y))/ det;

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


    public Functions getFirstFunc() {
        return firstFunc;
    }

    public void setFirstFunc(Functions firstFunc) {
        this.firstFunc = firstFunc;
    }

    public Functions getSecondFunc() {
        return secondFunc;
    }


    public void setfirstFuncStr(String firstFuncStr) {
        this.firstFuncStr = firstFuncStr;
    }

    public void setSecondFuncStr(String secondFuncStr) {
        this.secondFuncStr = secondFuncStr;
    }

}
