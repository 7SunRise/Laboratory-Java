import java.util.ArrayList;

//--------------------------------------------------------------------------------------------------

public class Hall {
    private int maxInRow = 0;                                                        // максимальное количество мест в ряду 
    private int total = 0;                                                           // общее количество мест в зале
    private int reserved = 0;                                                        // количество забронированных мест в зале
    private ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();     // список мест в зале
    
//--------------------------------------------------------------------------------------------------

    public Hall() {}                                  // конструктор без параметров
    public Hall(ArrayList<ArrayList<Seat>> seats) {   // конструктор с параметром списка мест
		this.setSeats(seats);
    }
    public Hall(Hall anotherHall) {                     // конструктор с параметром списка мест другого зала
		this.setSeats(anotherHall.getSeats());
    }

//----------------------------------------------------------------------------------------------------

    public ArrayList<ArrayList<Seat>> getSeats() {        // (МЕТОД) получить места в зале
		return seats;
    }
    public void setSeats(ArrayList<ArrayList<Seat>> seats) { // (МЕТОД) установить определенную расстановку мест
		this.seats.clear();
		this.maxInRow = 0;
		this.total = 0;
		this.reserved = 0;
		for (var row : seats) {
	    	ArrayList<Seat> newRow = new ArrayList<>();
	    	int seatCount = row.size();
	    	if (seatCount > maxInRow)
			maxInRow = seatCount;
	    	for (var seat : row) {
				Seat newSeat = new Seat(seat);
				newRow.add(newSeat);
				total++;
				reserved += newSeat.isReserved() ? 1 : 0;
	    	}
	    	this.seats.add(newRow);
		}
    }
    public void addRow(int seatCount) {                          // (МЕТОД) добавить ряд в зал
		ArrayList<Seat> newRow = new ArrayList<>();
		if (seatCount < 0)
	    	return;
		if (seatCount > maxInRow)
	    	maxInRow = seatCount;
		for (int i = 0; i < seatCount; i++) {
	    	Seat newSeat = new Seat();
	    	newRow.add(newSeat);
		}
		total += seatCount;
		seats.add(newRow);
    }
    public void addRows(int[] seatsPerRow) {                         // (МЕТОД) добавить несколько рядов в зал
		for (var seatCount : seatsPerRow)
	    	addRow(seatCount);
    }
    public boolean isSeatReserved(int row, int number) {            // (МЕТОД) проверить забронировано ли место
		return seats.get(row).get(number).isReserved();
    }
    public void reserveSeat(int row, int number) {                   // (МЕТОД) забронировать место
		seats.get(row).get(number).reserve();
		reserved++;
    }
    public void cancelReservationSeat(int row, int number) {         // (МЕТОД) снять бронь с места
		seats.get(row).get(number).cancelReservation();
		reserved--;
    }
    public int getTotalSeats() {                                    // (МЕТОД) получить общее число мест в зале
		return total;
    }
    public int getReservedSeats() {                                 // (МЕТОД) получить количество зарезервированных мест в зале
		return reserved;
    }
    public int getFreeSeats() {                                    // (МЕТОД) получить количество свободных мест в зале
		return total - reserved;
    }
    @Override
    public String toString() {            // (ПЕРЕОПРЕДЕЛЕНИЕ) (МЕТОД) получить рассадку зала
		String output = "";
		for (int i = 0; i < seats.size(); i++) {
	    	var row = seats.get(i);
	    	int notSeats = maxInRow - row.size();
	    	for (int j = 0; j < notSeats/2; j++)
				output += "  ";
	    	for (var seat : row) 
				output += seat.isReserved() ? "X " : "O "; // X - забронировано, O - свободно
	    	for (int j = 0; j < notSeats - notSeats/2; j++)
				output += "  ";
	    	output += "\n";
		}
		return output;
    }
}