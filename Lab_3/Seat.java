public class Seat {                     // класс места в зале
  private boolean isReserved = false;   

//-----------------------------------------------------------------------------------------------------

  public Seat() {}                    // конструктор без параметров
  public Seat(boolean isReserved) {   // конструктор с параметром значения брони
		this.isReserved = isReserved;
  }
  public Seat(Seat anotherSeat) {     // конструктор с параметром значения брони другого места
		this.isReserved = anotherSeat.isReserved();
	}

//-----------------------------------------------------------------------------------------------------

  public boolean isReserved() {             // (МЕТОД) проверить бронь места
		return isReserved;
  }
  public void reserve() {                  // (МЕТОД) забронировать место
		this.isReserved = true;
  }
  public void cancelReservation() {        // (МЕТОД) убрать бронь с места
		this.isReserved = false;
  }
}