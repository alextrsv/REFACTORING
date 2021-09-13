package gui;

public class BsectionMethod extends SolvingMethod {

    public String solve(){

        if(checkSuffCondition(leftBorder, rightBorder)) {
            while (Math.abs(rightBorder - leftBorder) > accuracy) {
                currentX = (leftBorder + rightBorder) / 2;
                if (function.solve(leftBorder, 0) * function.solve(currentX, 0) > 0)
                    leftBorder = currentX;
                else rightBorder = currentX;
                steps++;
            }
            return String.format("%4.10f ", currentX);
        }else return "уравнене не имеет корней или имеет более одного корня на данном интервале";
    }


    @Override
    public String toString() {
        return "метод половинного деления";
    }

    public static String getNameOfMethod() {
        return "метод половинного деления";
    }
}
