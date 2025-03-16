import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

//-------------------------------------------------------------------------------------------

public class User {
    private String username = "";                                            // имя пользователя
    private String password = "";                                            // пароль
    private ArrayList<filmSession> schedule = new ArrayList<filmSession>();  // расписание сеансов фильмов
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();             // кинотеатры
    private boolean logged = false;                                          // зарегистрированность
    private boolean admin = false;                                           // статус администратора

//------------------------------------------------------------------------------------------

    public User() {}                                                    // конструктор без параметров
    public User(String username, String password) {                     // конструктор с параметрами имени и пароль пользователя
		this.username = username;
		this.password = password;
    }
    public User(String username, String password, boolean isAdmin) {    // конструктор с параметрами имени, пароля и статуса 
		this.username = username;
		this.password = password;
		this.admin = isAdmin;
    }
  
//---------------------------------------------------------------------------------------------

    public boolean login(String username, String password) {            // (МЕТОД) войти в систему
		logged = (this.username.equals(username) && this.password.equals(password));
		return logged;
    }
    public String getUsername() {                                        // (МЕТОД) получить имя пользователя
		return username;
    }
    public String getPassword() {                                         // (МЕТОД) получить пароль
		return password;
    }
    public ArrayList<Cinema> getCinemaList() {                             // (МЕТОД) получить список кинотеатров
		return cinemas;
    }
    public ArrayList<filmSession> getSchedule() {                       // (МЕТОД) получить расписание сеансов
		return schedule;
    }
    public boolean isLoggedIn() {                                        // (МЕТОД) проверить регистрацию
		return logged;
    }
    public boolean isAdmin() {                                           // (МЕТОД) проверить статус администратора
		return admin;
    }
    public void setCinemaList(ArrayList<Cinema> cinemas) {             // (МЕТОД) установить список кинотеатров
		this.cinemas = cinemas;
    }
    public void setSchedule(ArrayList<filmSession> schedule) {       // (МЕТОД) установить расписание сеансов
		this.schedule = schedule;
    }
    public void setUsername(String username) {                      // (МЕТОД) установить имя пользователя
		this.username = username;
    }
    public void setPassword(String password) {                    // (МЕТОД) установить пароль
		this.password = password;
    }
    public String scheduleToString() {                           // (МЕТОД) вывести расписание сеансов фильмов
		String output = "";
		for (int i = 0; i < schedule.size(); i++)
	    	output += "Сеанс %d\n%s\n".formatted(i+1, schedule.get(i).toString());
		return output;
    }
    public String cinemasToString() {                                        // (МЕТОД) вывести названия кинотеатров
		String output = "";
		for (int i = 0; i < cinemas.size(); i++)
	    	output += "Кинотеатр %d - %s\n".formatted(i+1, cinemas.get(i).getName());
		return output;
    }
    public String hallScheme(int cinemaIndex, int hallIndex) {                // (МЕТОД) вывести рассадку зала
		Cinema cinema = null;
		try {
	    	cinema = cinemas.get(cinemaIndex);
		}
		catch (Exception e) {
	    	return "Ошибка! Такого кинотеатра нет.";
		}
		try {
	    	return cinema.getHalls().get(hallIndex).toString();
		}
		catch (Exception e) {
	    	return "Ошибка! Такого зала нет в данном кинотеатре";
		}
    }
    public filmSession findClosestScreening(String movieName, Calendar date) {           // (МЕТОД) найти ближайший сеанс нужного фильма
		filmSession closest = null;
		long maximumWait = Long.MAX_VALUE;
		for (var screening : schedule) {
	    	if (screening.getFilmName().equals(movieName) && screening.getDate().after(date)) {
				var beginTime = screening.getDate().getTimeInMillis();
				var curTime = date.getTimeInMillis();
				if (beginTime - curTime < maximumWait) {
		    		maximumWait = beginTime - curTime;
		    		closest = screening;
				}
	    	}
		}
		return closest;
    }
    public ArrayList<filmSession> findScreenings(Object... params) {                  // (МЕТОД) найти подходящий сеанс
		ArrayList<filmSession> query = new ArrayList<filmSession>();
		Calendar date = null;
		Cinema cinema = null;
		String movieName = null;
		int hallNumber = -1;
		for (var param : params) {
	    	if (param instanceof Calendar)
				date = (Calendar)param;
	    	else if (param instanceof Cinema)
				cinema = (Cinema)param;
	    	else if (param instanceof String)
				movieName = (String)param;
	    	else if (param instanceof Integer)
				hallNumber = (Integer)param;
		}
		for (var screening : schedule) {
	    	boolean toAdd = true;
	    	if (date != null)
				toAdd = screening.getDate().equals(date);
	    	if ((cinema != null) && toAdd)
				toAdd = screening.getCinema().equals(cinema);
	    	if ((movieName != null) && toAdd)
				toAdd = screening.getFilmName().equals(movieName);
	    	if ((hallNumber > -1) && toAdd)
				toAdd = (screening.getHallNumber() == hallNumber);
	    	if (toAdd)
				query.add(screening);
		}
		return query;
    }
	public void makeReservation(int screeningIndex) {                      // (МЕТОД) забронировать место
		try {
	    	var hall = schedule.get(screeningIndex).getHall();
	    	if (hall.getFreeSeats() < 1)
				System.out.println("Ошибка: Не достаточно свободных мест в зале");
			else
	    		buyingTickets(hall);
		}
		catch (Exception e) {
	    	System.out.println("Ошибка: Неверный индекс сеанса фильма");
		}
    }
    private void buyingTickets(Hall hall) {                              // (МЕТОД) купить билет
		Scanner in = new Scanner(System.in);
	    System.out.println(hall.toString());
	    System.out.println("Выберите место:");
	    System.out.print("Ряд: ");
	    var row = in.nextInt() - 1;
	    System.out.print("Номер места: ");
	    var num = in.nextInt() - 1;
	    try {
			if (hall.isSeatReserved(row, num))
		    	System.out.println("Ошибка! Это место уже забронировано");
			else {
		    	hall.reserveSeat(row, num);
			}
	    }
	    catch (Exception e){
			System.out.println("Ошибка! Такого места нет.");
	    }
		in.close();
	}
	public void addCinema(String cinemaName) {                          // (МЕТОД) (АДМИНИСТРАТОР) добавить кинотеатр
		if (!admin)
	    	System.out.println("Ошибка: Недостаточно прав для этого действия");
		Cinema newCinema = new Cinema(cinemaName);
		cinemas.add(newCinema);
    }
	public void removeCinema(int cinemaIndex) {                         // (МЕТОД) (АДМИНИСТРАТОР) удалить кинотеатр
		if (!admin)
	    	System.out.println("Ошибка: Недостаточно прав для этого действия");
		try {
	    	var cinema = cinemas.get(cinemaIndex);
	    	var toBeRemoved = findScreenings(cinema);
	    	for (var screening : toBeRemoved)
			schedule.remove(screening);
	    	cinemas.remove(cinemaIndex);
		}
		catch (Exception e) {
	    	System.out.println("Ошибка: Неверный индекс кинотеатра");
		}
    }
	public void addHall(int cinemaIndex, int[] seatsPerRow) {             // (МЕТОД) (АДМИНИСТРАТОР) добавить зал в кинотеатр
		if (!admin)
	    	System.out.println("Ошибка: Недостаточно прав для этого действия");
		Hall hall = new Hall();
		hall.addRows(seatsPerRow);
		try {
	    	cinemas.get(cinemaIndex).getHalls().add(hall);
		}
		catch (Exception e) {
	    	System.out.println("Ошибка: Неверный индекс кинотеатра");
		}
    }
	public void removeHall(int cinemaIndex, int hallIndex) {               // (МЕТОД) (АДМИНИСТРАТОР) удалить зал из кинотеатра
		if (!admin)
			System.out.println("Ошибка: Недостаточно прав для этого действия");
		Cinema cinema = null;
		try {
	    	cinema = cinemas.get(cinemaIndex);
		}
		catch (Exception e) {
	    	System.out.println("Ошибка: Неверный индекс кинотеатра");
		}
		try{
	    	cinema.getHalls().remove(hallIndex);
	    	var toBeChanged = findScreenings(cinema);
	    	for (var screening : toBeChanged) {
				var oldIndex = screening.getHallNumber() - 1;
				if (oldIndex == (hallIndex))
		    		schedule.remove(screening);
				else if (oldIndex > (hallIndex))
		    		screening.setHallNumber(oldIndex);
	    	}
		}
		catch (Exception e) {
	    	System.out.println("Ошибка: Неверный индекс зала");
		}
    }
	public void removeRowFromHall(int cinemaIndex, int hallIndex, int rowIndex) {          // (МЕТОД) (АДМИНИСТРАТОР) удалить ряд из зала
		if (!admin)
			System.out.println("Ошибка: Недостаточно прав для этого действия");
		Cinema cinema = null;
		Hall hall = null;
		try {
	    	cinema = cinemas.get(cinemaIndex);
		}
		catch (Exception e) {
			System.out.println("Ошибка: Неверный индекс кинотеатра");
		}
		try {
	    	hall = cinema.getHalls().get(hallIndex);
		}
		catch (Exception e) {
			System.out.println("Ошибка: Неверный индекс зала");
		}
		try {
	    	hall.getSeats().remove(rowIndex);
	    	var seatsToBeDeleted = findScreenings(cinema, hallIndex);
	    	for (var screening : seatsToBeDeleted)
				screening.getHall().getSeats().remove(rowIndex);
		}
		catch (Exception e) {
			System.out.println("Ошибка: Неверный индекс ряда");
		}
    }
    private boolean checkOverlap(filmSession screening) {            // (МЕТОД) (АДМИНИСТРАТОР) проверить на пересечение сеансов фильмов по времени
		var begin = screening.getDate().getTimeInMillis();
		var end = begin + screening.getDuration()*60000; 
		var otherScreenings = findScreenings(screening.getCinema(), screening.getHallNumber());
		for (var other : otherScreenings) {
	    	var otherBegin = other.getDate().getTimeInMillis();
	    	var otherEnd = otherBegin + other.getDuration()*60000;
	    	if (((otherBegin <= begin) && (begin <= otherEnd)) || ((otherBegin <= end) && (end <= otherEnd)) ||  ((begin <= otherBegin) && (otherBegin <= end)))  
				return true;
		}
		return false;
    }
	public void addScreening(Calendar date, int cinemaIndex, String title, long duration, int hallIndex) {           // (МЕТОД) (АДМИНИСТРАТОР) добавить сеанс фильма
		if (!admin)
			System.out.println("Ошибка: Недостаточно прав для этого действия");
		Cinema cinema = null;
		try {
	    	cinema = cinemas.get(cinemaIndex);
		}
		catch (Exception e) {
			System.out.println("Ошибка: Неверный индекс кинотеатра");
		}
		try {
	    	cinema.getHalls().get(hallIndex);
		}
		catch (Exception e) {
	    	System.out.println("Ошибка: Неверный индекс зала");
		}
		filmSession newScreening = new filmSession(date, cinema, title, duration, hallIndex);
		if (checkOverlap(newScreening))
			System.out.println("Ошибка: Пересечение сеансов двух разных фильмов");
		schedule.add(newScreening);
    }
	public void removeScreening(int screeningIndex) {                          // (МЕТОД) (АДМИНИСТРАТОР) удалить сеанс фильма
		if (!admin)
	    	System.out.println("Ошибка: Недостаточно прав для этого действия");
		try {
	    	schedule.remove(screeningIndex);
		}
		catch (Exception e) {
	    	System.out.println("Ошибка: Неверный индекс сеанса");
		}
    }
}