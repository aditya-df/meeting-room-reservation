package tkba.team6.roomreservationsystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tkba.team6.roomreservationsystem.db.entity.Reservations;
import tkba.team6.roomreservationsystem.db.entity.Rooms;
import tkba.team6.roomreservationsystem.db.model.reservation.ReservationService;
import tkba.team6.roomreservationsystem.db.model.room.RoomsService;
import tkba.team6.roomreservationsystem.db.repository.ReservationRepository;

import java.sql.Time;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	private static boolean isTimeGreaterThan(Time time1, Time time2) {
		return time1.compareTo(time2) >= 0;
	}

	private static boolean isTimeLowerThan(Time time1, Time time2) {
		return time1.compareTo(time2) <= 0;
	}

	@Autowired
	private ReservationService ReservationService; 

	@Autowired
	private ReservationRepository ReservationRepository;

	@Autowired
	private RoomsService RoomsService;

	@GetMapping("")
	public String index(HttpServletRequest request, Model model) {
		List<Reservations> ReservationList = ReservationService.getAllReservation();
		System.out.println(ReservationList);

		model.addAttribute("ReservationList", ReservationList);

		return "page/reservation/index";
	}

	@GetMapping("/create")
	public String create(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Rooms> RoomList = RoomsService.getAllRooms();

		model.addAttribute("RoomList", RoomList);

		return "page/reservation/create";
	}

	@PostMapping("/create")
	public String createConfirm(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws Exception {
		HttpSession session = request.getSession();

		List<Rooms> RoomList = RoomsService.getAllRooms();

		model.addAttribute("RoomList", RoomList);

		try {
			Long roomId = Long.parseLong(request.getParameter("room"));

			Rooms Room = RoomsService.getRoomById(roomId);
			if (Room == null) {
				throw new Exception("Room Invalid");
			}
	
			String dateRequest = request.getParameter("date");
			Date date = Date.valueOf(dateRequest);
			
			String startTimeRequest = request.getParameter("startTime");
			DateFormat formatterTime = new SimpleDateFormat("HH:mm");
			Time startTime = new Time(formatterTime.parse(startTimeRequest).getTime());
	
			String endTimeRequest = request.getParameter("endTime");
			Time endTime = new Time(formatterTime.parse(endTimeRequest).getTime());

			if (isTimeLowerThan(endTime, startTime)) {
				throw new Exception("End time bigger that start time");
			}

			String snackRequest = request.getParameter("snackRequest");
	
			Reservations Reservations = new Reservations();
			Reservations.setRequest_user((long)1);
			Reservations.setRoom(Room);
			Reservations.setRequestDate(date);
			Reservations.setStartTime(startTime);
			Reservations.setEndTime(endTime);
			Reservations.setStatus("pending");
			Reservations.setRequest_snack(snackRequest);

			List<String> RoomStatus = new ArrayList<>();
			RoomStatus.add("pending");
			RoomStatus.add("approved");
			List<Reservations> RoomListOnUse = ReservationService.findByStatusInAndRequestDate(RoomStatus, date);
			for (Reservations roomTemp : RoomListOnUse) {
				Time startTimeTemp = roomTemp.getStartTime();
				Time endTimeTemp = roomTemp.getEndTime();
				if (isTimeGreaterThan(startTime, startTimeTemp) && isTimeLowerThan(startTime, endTimeTemp)) {
					throw new Exception("Room has been reservation on this time");
				}
				
				if (isTimeGreaterThan(endTime, startTimeTemp) && isTimeLowerThan(endTime, endTimeTemp)) {
					throw new Exception("Room has been reservation on this time");
				}

				if (isTimeLowerThan(startTime, startTimeTemp) && isTimeGreaterThan(endTime, endTimeTemp)) {
					throw new Exception("Room has been reservation on this time");
				}
			}
	
			Reservations ReservationsInsert = ReservationRepository.save(Reservations);

			response.sendRedirect(request.getContextPath() + "/reservation/" + ReservationsInsert.getId());
		} catch (Exception e) {
			session.setAttribute("errorMessage", e.getMessage());
		}

		return "page/reservation/create";
	}

	@GetMapping("/review")
	public String review(HttpServletRequest request, Model model) {
		List<Reservations> ReservationList = ReservationService.getReservationOnPending();

		model.addAttribute("ReservationList", ReservationList);

		return "page/reservation/review";
	}

	@PostMapping("/{action}/{id}")
	public void approve(HttpServletRequest request, HttpServletResponse response, @PathVariable String action, @PathVariable Long id) 
			throws Exception {
		HttpSession session = request.getSession();
	
		try {
			switch (action) {
				case "approve":
					ReservationService.approveReservation(id);
					break;
				case "reject":
					ReservationService.rejectReservation(id);
					break;
			}
		} catch (Exception e) {
			session.setAttribute("errorMessage", e.getMessage());
		}

		response.sendRedirect(request.getContextPath() + "/reservation/review");
	}

	@GetMapping("/{id}")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable Long id)
			throws Exception {
		HttpSession session = request.getSession();

		try {
			Reservations Reservation = ReservationService.getReservationById(id);

			if (Reservation == null) {
				throw new NullPointerException("invalid reservatio");
			}

			model.addAttribute("Reservation", Reservation);
		} catch (NullPointerException e) {
			session.setAttribute("errorMessage", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/reservation");
		}

		return "page/reservation/detail";
	}
}
