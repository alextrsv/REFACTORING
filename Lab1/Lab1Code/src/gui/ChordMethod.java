package gui;

public class ChordMethod extends SolvingMethod{
    double previousX;
    double step = 0.5;//Math.abs(rightBorder  leftBorder) / 100.0;

    @Override
    public String solve() {

        if(checkSuffCondition(leftBorder, rightBorder)) {

            currentX = leftBorder;

            while (Math.abs(currentX - previousX) > accuracy || Math.abs(function.getFunc().solve(currentX,0)) > accuracy){
                previousX = currentX;

                currentX = (leftBorder * function.getFunc().solve(rightBorder,0) - rightBorder * function.getFunc().solve(leftBorder,0))/
                        (function.getFunc().solve(rightBorder,0) - function.getFunc().solve(leftBorder,0));
                if (function.getFunc().solve(leftBorder,0) * function.getFunc().solve(currentX,0) > 0)
                    leftBorder = currentX;
                else rightBorder = currentX;
                steps++;
            }
            return String.format("%4.10f ", currentX);
        }else return "уравнене не имеет корней \nили имеет более одного корня на данном интервале";
    }


    public static String getNameOfMethod() {
        return "метод хорд";
    }

}
