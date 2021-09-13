package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AppController {

    @FXML private ComboBox<String> comboChangeTask;
    @FXML private ComboBox<String> equationsBox;
    @FXML private TextField leftBorderInput;
    @FXML private TextField rightBorderInput;
    @FXML private TextField accuracyInput;
    @FXML private ComboBox<String> methodOfSolvingBox;
    @FXML private Button solveBtn;
    @FXML private Label solveOut;
    @FXML private LineChart<Double, Double> chart;

    Functions currentFunction = (n,a)-> (n*n + 25*n);
    SolvingMethod currentMethod = new BsectionMethod();
    int currentFunctionIndx = 0;
    int currentMethodIndx = 0;

    // ДИННЫЙ МЕТОД
    @FXML
    void solveBtnPressed(ActionEvent event) {
        double leftBorder = Double.parseDouble(leftBorderInput.getText());
        double rightBorder = Double.parseDouble(rightBorderInput.getText());

        currentMethod.initParams(leftBorder, rightBorder, Double.parseDouble(accuracyInput.getText()), currentFunction, methodOfSolvingBox.getValue());

        solveOut.setText(currentMethod.solve());
        createChart(0.02, leftBorder, rightBorder);
    }

    void createChart(double step, double lBorder, double rBorder){
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        for (double x = lBorder; x <rBorder; x = x + step) {
            series.getData().add(new XYChart.Data<>(x, currentFunction.solve(x,0)));
        }
        // сформированный массив точек, передаем графику для отображения
        chart.getData().setAll(series);
    }


    // Тоже длинный метод (далил лишний код)
    @FXML
    void changeTask(ActionEvent event) {
        Main.setModeIndx(1);
        Stage stage  = (Stage)comboChangeTask.getScene().getWindow();
        StageLoader.loadStage(stage, "\\fxml\\system.fxml");
    }


    @FXML
    void chooseMethod(ActionEvent event) {
        switch (methodOfSolvingBox.getValue()) {
            case "метод половинного деления":
                currentMethodIndx = 0;
                currentMethod = new BsectionMethod();
                break;
            case "метод хорд":
                currentMethodIndx = 1;
                currentMethod = new ChordMethod();
                break;
        }
    }


    @FXML
    void chooseEq(ActionEvent event) {
        switch (equationsBox.getValue()){
            case "x^2 + 25x = 0":
                currentFunctionIndx = 0;
                currentFunction = (n, a)-> (n*n + 25*n);
                break;
            case "x^3+23x-56 = 0":
                currentFunctionIndx = 1;
                currentFunction = (n,a)-> (n*n*n + 23*n - 56);
                break;
            case "sin(x)":
                currentFunctionIndx = 2;
                currentFunction = (n,a)-> (Math.sin(n));
                break;
            case "x^3 - x + 4":
                currentFunctionIndx = 3;
                currentFunction = (n,a)-> (n*n*n - n + 4);
                break;
        }

    }


    @FXML
    void initialize() {
        chart.setCreateSymbols(false);
        //
        ObservableList<String> modeList = FXCollections.observableArrayList("Решение уравнения", "Решение системы уравнений");

        comboChangeTask.setItems(modeList);
        comboChangeTask.setValue(modeList.get(Main.getModeIndx())); // устанавливаем выбранный элемент по умолчанию

        //
        ObservableList<String> functionsList = FXCollections.observableArrayList("x^2 + 25x = 0", "x^3+23x-56 = 0",
                "sin(x)", "x^3 - x + 4");


        equationsBox.setItems(functionsList);
        equationsBox.setValue(functionsList.get(currentFunctionIndx)); // устанавливаем выбранный элемент по умолчанию


        //
        ObservableList<String> methodsList = FXCollections.observableArrayList(BsectionMethod.getNameOfMethod(),
                ChordMethod.getNameOfMethod());

        methodOfSolvingBox.setItems(methodsList);
        methodOfSolvingBox.setValue(methodsList.get(currentMethodIndx)); // устанавливаем выбранный элемент по умолчанию

    }
}
