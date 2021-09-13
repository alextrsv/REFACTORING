package gui;

public enum Function {
    SINGLE_FIRST  (((n, a)-> (n*n + 25*n)), "x^2 + 25x = 0",0),
    SINGLE_SECOND (((n,a)-> (n*n*n + 23*n - 56)), "x^3+23x-56 = 0",1),
    SINGLE_THIRD  (((n,a)-> (Math.sin(n))), "sin(x)",2),
    SINGLE_FOURTH (((n,a)-> (n*n*n - n + 4)), "x^3 - x + 4", 3),

    SYS_FIRST  (((x, y)-> (y - x*x*x - 4.0)), "y - x^3 - 4 = 0", 1),
    SYS_SECOND (((x, y)-> ( y + Math.exp(x) + 1)),"y + e^x + 1 = 0",2),
    SYS_THIRD  (((x, y)-> (x * x + y)),"x^2 + y = 0", 3),

    None (null, "none", -1)
    ;

    private Functions func;
    private String functionText;
    private int index;

    Function(){}

    Function(Functions func, String functionText, int index){
        this.func = func;
        this.functionText = functionText;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
