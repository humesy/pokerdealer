import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class BarChartSample extends Application {
    final static String high = "High Card";
    final static String pair = "Pair";
    final static String twopair = "Two Pair";
    final static String trips = "Trips";
    final static String straight = "Straight";
    final static String flush = "Flush";
    final static String book = "Full House";
    final static String quads = "Quads";
    final static String strflush = "Straight Flush";
    final static String royal = "Royal Flush";
    
    int[] count;
 
    //public BarChartSample(int[] c)
    //{
        //count = c;
    //}
    
    @Override public void start(Stage stage) {
        stage.setTitle("Poker Hands");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Poker Hands");
        xAxis.setLabel("Hand");       
        yAxis.setLabel("Value");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Hands");       
        series1.getData().add(new XYChart.Data(high, count[0]));
        series1.getData().add(new XYChart.Data(pair, count[1]));
        series1.getData().add(new XYChart.Data(twopair, count[2]));
        series1.getData().add(new XYChart.Data(trips, count[3]));
        series1.getData().add(new XYChart.Data(straight, count[4]));
        series1.getData().add(new XYChart.Data(flush, count[5]));
        series1.getData().add(new XYChart.Data(book, count[6]));
        series1.getData().add(new XYChart.Data(quads, count[7]));
        series1.getData().add(new XYChart.Data(strflush, count[8]));
        series1.getData().add(new XYChart.Data(royal, count[9]));    
         
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
 
    public void run(int[] c) {
        count = c;
        launch();
    }
}