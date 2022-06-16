package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.exceptions.DomainException;

public class Reservation {
	static Date now = new Date();
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;

	public Reservation() {
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Error in reservation: Reservation dates must be future dates");
		}
		if(checkOut.before(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;

	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public Integer duration() {

		long checkInMil = checkIn.getTime() / 1000L / 60L / 60L / 24L;
		long checkOutMil = checkOut.getTime() / 1000L / 60L / 60L / 24L;

		return (int) (checkOutMil - checkInMil);

	}

	public void updateDate(Date checkIn, Date checkOut) {
	
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Error in reservation: Reservation dates for update must be future dates");
		}
		if(checkOut.before(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
			
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return "Reservation: Room " 
				+ getRoomNumber() 
				+ ", check-in: " 
				+ sdf.format(getCheckIn()) 
				+ ", check-out: "
				+ sdf.format(getCheckOut()) 
				+ ", " 
				+ duration() 
				+ " nights";

	}
}
