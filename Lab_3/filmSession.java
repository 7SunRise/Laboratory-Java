import java.util.Calendar;

//--------------------------------------------------------------------------------------------------------------------------------------------

public class filmSession {     // класс сеанса фильма
  private Calendar date;       // дата и время сеанса
  private Cinema cinema;       // кинотеатр
  private String filmName;     // название фильма
  private long duration;       // длительность фильма
  private int hallNumber;      // номер зала в кинотеатре
  private Hall hall;           // зал

//------------------------------------------------------------------------------------------------------------------------------------------

  public filmSession(Calendar date, Cinema cinema, String filmName, long duration, int hallNumber) { // конструктор для показа фильма
	  this.date = date;
		this.cinema = cinema;
		this.filmName = filmName;
		this.duration = duration;
		this.hallNumber = hallNumber;
		this.hall = new Hall(cinema.getHalls().get(hallNumber)); 
  }

//--------------------------------------------------------------------------------------------------------------------------------------------

  public Calendar getDate() {                            // (МЕТОД) получить дату и время сеанса
		return date;
  }
  public Cinema getCinema() {                           // (МЕТОД) получить кинотеатр, в котором пройдет сеанс
		return cinema;
  }
  public String getFilmName() {                        // (МЕТОД) получить название фильма
		return filmName;
  }
  public long getDuration() {                         // (МЕТОД) получить длительность фильма
		return duration;
  }
  public int getHallNumber() {                       // (МЕТОД) получить номер зала
		return hallNumber;
  }
  public Hall getHall() {                           // (МЕТОД) получить зал
		return hall;
  }
  public void setDate(Calendar date) {              // (МЕТОД) установить дату и время сеанса
		this.date = date;
  }
  public void setCinema(Cinema cinema) {           // (МЕТОД) установить кинотеатр, в котором пройдет сеанс
		this.cinema = cinema;
  }
  public void setFilmName(String movieName) {     // (МЕТОД) установить название фильма
		this.filmName = movieName;
  }
  public void setDuration(long duration) {         // (МЕТОД) установить длительность фильма
		this.duration = duration;
  }
  public void setHallNumber(int hallNumber) {      // (МЕТОД) установить номер зала
		this.hallNumber = hallNumber;
  }
  public void setHall(Hall hall) {                // (МЕТОД) установить зал
		this.hall = new Hall(hall);
  }
  @Override
  public String toString() {                     // (ПЕРЕОПРЕДЕЛЕНИЕ) (МЕТОД) получить информацию о сеансе фильма, а также свободные места
		return "Дата: %s\nКинотеатр: %s\nНазвание: %s\nДлительность (в минутах): %d\nНомер зала: %d\nСвободных мест: %d\nСхема зала:\n%s".formatted(date.getTime(), cinema.getName(), filmName, duration, 1 + hallNumber, hall.getFreeSeats(), hall.toString());
  }
}