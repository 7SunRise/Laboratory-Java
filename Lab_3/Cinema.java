import java.util.ArrayList;

//-----------------------------------------------------------------------

public class Cinema {                                        // класс кинотеатра
  private String name;                                       // название кинотеатра
  private ArrayList<Hall> halls = new ArrayList<Hall>();     // список залов в кинотеатре

//----------------------------------------------------------------------

  public Cinema(String name) {      // конструктор с названием кинотеатра
		this.name = name;
  }

//-------------------------------------------------------------------------

  public String getName() {            // (МЕТОД) получить название кинотеатра
		return name;
  }
  public ArrayList<Hall> getHalls() {  // (МЕТОД) получить список залов кинотеатра
		return halls;
  }
  @Override
  public String toString() { // (ПЕРЕОПРЕДЕЛЕНИЕ) (МЕТОД) получить вид залов кинотеатра
		String output = "";
		for (int i = 0; i < halls.size(); i++)
	    output += "Зал %d\n%s".formatted(i+1, halls.get(i).toString());
		return output;
  }
}