package gui;

public enum Function {
    FIRST  (0, ((n, a)-> (n*n + 25*n)), "x^2 + 25x = 0" ),
    SECOND (1, ((n,a)-> (n*n*n + 23*n - 56)), "x^3+23x-56 = 0" ),
    THIRD  (2, ((n,a)-> (Math.sin(n))), "sin(x)" ),
    FOURTH (3, ((n,a)-> (n*n*n - n + 4)), "x^3 - x + 4" )

    ;
    int index;
    Functions func;
    String functionText;

    Function(){}

    Function(int index, Functions func, String functionText){
        this.index = index;
        this.func = func;
        this.functionText = functionText;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Functions getFunc() {
        return func;
    }

    public void setFunc(Functions func) {
        this.func = func;
    }

    public String getFunctionText() {
        return functionText;
    }

    public void setFunctionText(String functionText) {
        this.functionText = functionText;
    }
}
