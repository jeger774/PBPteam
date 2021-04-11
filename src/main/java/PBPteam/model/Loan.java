package PBPteam.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Loan extends Product {
    private final StringProperty timeLeft;
    public Loan() {
        super();
        timeLeft = new SimpleStringProperty();
    }
    //set
    public void setTimeLeft(String timeLeft){
        this.timeLeft.set(timeLeft);
    }
    //get
    public String getTimeLeft(){
        return this.timeLeft.get();
    }
}
