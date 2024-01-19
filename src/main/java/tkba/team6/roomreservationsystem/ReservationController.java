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
import tkba.team6.roomreservationsystem.db.model.reservation.ReservationService;
import tkba.team6.roomreservationsystem.db.repository.ReservationRepository;
import java.sql.Time;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationService ReservationService; 

	@Autowired
	private ReservationRepository ReservationRepository;

	@GetMapping("")
	public String index(HttpServletRequest request, Model model) {
		List<Reservations> ReservationList = ReservationService.getAllReservation();

		model.addAttribute("ReservationList", ReservationList);

		return "page/reservation/index";
	}

	@GetMapping("/create")
	public String create(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "page/reservation/create";
	}

	@PostMapping("/create")
	public String createConfirm(HttpServletRequest request, HttpServletResponse response, Model model) 
		throws Exception {
		try {
			Long room = Long.parseLong(request.getParameter("room"));
	
			String dateRequest = request.getParameter("date");
			Date date = Date.valueOf(dateRequest);
			
			String startTimeRequest = request.getParameter("startTime");
			DateFormat formatterTime = new SimpleDateFormat("HH:mm");
			Time startTime = new Time(formatterTime.parse(startTimeRequest).getTime());
	
			String endTimeRequest = request.getParameter("endTime");
			Time endTIme = new Time(formatterTime.parse(endTimeRequest).getTime());

			String snackRequest = request.getParameter("snackRequest");
	
			Reservations Reservations = new Reservations();
			Reservations.setRequest_user((long)1);
			Reservations.setRoom_id(room);
			Reservations.setRequestDate(date);
			Reservations.setStartTime(startTime);
			Reservations.setEndTime(endTIme);
			Reservations.setStatus("pending");
			Reservations.setRequest_snack(snackRequest);

			List<String> RoomStatus = new ArrayList<>();
			RoomStatus.add("pending");
			RoomStatus.add("approved");
			List<Reservations> RoomList = ReservationService.findByStatusInAndRequestDate(RoomStatus, date);

			for (Reservations Room : RoomList) {
				
			}
			// LocalTime lt = LocalTime.parse("6:00 AM", 
            //     DateTimeFormatter.ofPattern("h:m a"));
	
			Reservations ReservationsInsert = ReservationRepository.save(Reservations);

			response.sendRedirect(request.getContextPath() + "/reservation" + ReservationsInsert.getId());
		} catch (ParseException e) {
			System.out.println(e.getMessage());
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
