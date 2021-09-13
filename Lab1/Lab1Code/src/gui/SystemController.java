package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.MethodAccessor_Integer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SystemController {

    @FXML private ComboBox<String> comboChangeTask;
    @FXML private ComboBox<String> firstEqBox;
    @FXML private ComboBox<String> secondEqBox;
    @FXML private TextField xInput;
    @FXML private TextField yInput;
    @FXML private TextField accuracyInput;
    @FXML private Label xSolveOut;
    @FXML private LineChart<Double, Double> chart;

    Function firstFunc, secondFunc;
    List<Function> functionList;

    @FXML
    void chooseFirstEq(ActionEvent event) {
        secondFunc = chooseFunc(firstEqBox);
    }

    @FXML
    void chooseSecondEq(ActionEvent event) {
        secondFunc = chooseFunc(secondEqBox);
    }

    private Function chooseFunc(ComboBox<String> box) {
        for (Function func: functionList) {
            if (func.getFunctionText().equals(box.getValue())) {
                return func;
            }
        }
        return Function.None;
    }

    Function changeFunc(Function function){
        switch(function.getFunctionText()){
            case "y - x^3 - 4":
                function.setFunc((x, y) -> (x * x * x  + 4.0));
                return function;
            case "y + e^x + 1":
                function.setFunc((x, y) -> (-Math.exp(x) - 1));
                return function;
            case "x^2 + y":
                function.setFunc((x, y) ->  -(x * x));
                return function;
        }
        return Function.None;
    }

    @FXML
    void solveBtnPressed(ActionEvent event) {
        NewtonMethod newtonMethod = new NewtonMethod(firstFunc, secondFunc, Double.parseDouble(xInput.getText()),
                Double.parseDouble(yInput.getText()), Double.parseDouble(accuracyInput.getText()));
        xSolveOut.setText(newtonMethod.solve());

        double step = 0.02;
        XYChart.Series<Double, Double> series1 = new XYChart.Series<>();
        XYChart.Series<Double, Double> series2 = new XYChart.Series<>();
        firstFunc.setFunctionText(firstFunc.getFunctionText().replace(" = 0", ""));
        secondFunc.setFunctionText(secondFunc.getFunctionText().replace(" = 0", ""));
        System.out.println("firstFuncStr: " + firstFunc.getFunctionText());
        System.out.println("secondFuncStr: " + secondFunc.getFunctionText());

        firstFunc = changeFunc(firstFunc);
        secondFunc = changeFunc(secondFunc);


        for (double x = -20; x <20; x = x + step) {
            series1.getData().add(new XYChart.Data<>(x, firstFunc.getFunc().solve(x,0)));
            series2.getData().add(new XYChart.Data<>(x, secondFunc.getFunc().solve(x,0)));
        }
//        // сформированный массив точек, передаем графику для отображения
        chart.getData().addAll(series1, series2);

    }

    @FXML
    void changeTask(ActionEvent event) {

        Main.setModeIndx(0);
        Stage stage  = (Stage)comboChangeTask.getScene().getWindow();
        StageLoader.loadStage(stage,"\\fxml\\single.fxml");
    }

    @FXML
    void initialize() {
        initParams();

        chart.setCreateSymbols(false);
        ObservableList<String> mode = FXCollections.observableArrayList("Решение уравнения", "Решение системы уравнений");

        comboChangeTask.setItems(mode);
        comboChangeTask.setValue(mode.get(Main.getModeIndx())); // устанавливаем выбранный элемент по умолчанию


        //
        ObservableList<String> EqList = FXCollections.observableArrayList("y - x^3 - 4 = 0", "y + e^x + 1 = 0", "x^2 + y = 0" );


        firstEqBox.setItems(EqList);
        firstEqBox.setValue(EqList.get(Main.getModeIndx())); // устанавливаем выбранный элемент по умолчанию

        //
        secondEqBox.setItems(EqList);
        secondEqBox.setValue(EqList.get(Main.getModeIndx())); // устанавливаем выбранный элемент по умолчанию
    }

    private void initParams() {
        functionList = new ArrayList<>();
        functionList.add(Function.SYS_FIRST);
        functionList.add(Function.SYS_SECOND);
        functionList.add(Function.SYS_THIRD);

        firstFunc = secondFunc = Function.SYS_FIRST;
    }

}
