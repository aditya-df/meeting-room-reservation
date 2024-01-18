package tkba.team6.roomreservationsystem;

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
	public String createConfirm(HttpServletRequest request, HttpServletResponse response, Model model) {
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
	
			Reservations ReservationsInsert = ReservationRepository.save(Reservations);

			System.out.println(ReservationsInsert.getId());
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

	@PostMapping("/approve/{id}")
	public void approve(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) 
			throws Exception {
		HttpSession session = request.getSession();

		try {
			ReservationService.approveReservation(id);
		} catch (Exception e) {
			session.setAttribute("errorMessage", e.getMessage());
		}

		response.sendRedirect(request.getContextPath() + "/reservation/review");
	}

	@PostMapping("/reject/{id}")
	public void reject(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) 
			throws Exception {
		HttpSession session = request.getSession();

		try {
			ReservationService.rejectReservation(id);
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

		} catch (NullPointerException e) {
			session.setAttribute("errorMessage", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/reservation");
		}

		return "page/reservation/detail";
	}
}
