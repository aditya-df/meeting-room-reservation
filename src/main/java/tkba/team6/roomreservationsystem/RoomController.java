package tkba.team6.roomreservationsystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tkba.team6.roomreservationsystem.db.entity.Rooms;
import tkba.team6.roomreservationsystem.db.model.room.RoomsService;
import tkba.team6.roomreservationsystem.db.repository.RoomRepository;

@Controller
@RequestMapping("/room")
public class RoomController {
	@Autowired
	private RoomsService RoomsService;

	@Autowired
	private RoomRepository RoomRepository;
 
	@GetMapping("")
	public String index(Model model) {
		List<Rooms> RoomList = RoomsService.getAllRooms();

		model.addAttribute("RoomList", RoomList);

		return "page/room/index";
	}

	@GetMapping("/create")
	public String create(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		return "page/room/create";
	}

	@PostMapping("/create")
	public String createConfirm(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();

		try {
			String roomName = request.getParameter("name");
			Integer roomCapacity = Integer.valueOf(request.getParameter("capacity"));

			Rooms Rooms = new Rooms();
			Rooms.setName(roomName);
			Rooms.setCapacity(roomCapacity);

			Rooms RoomsInsert = RoomRepository.save(Rooms);

			return "redirect:/room/" + RoomsInsert.getId();
		} catch (Exception e) {
			session.setAttribute("errorMessage", e.getMessage());
		}

		return "redirect:page/room/create";
	}

	@GetMapping("/{id}")
	public String detail(Model model, @PathVariable Long id) {
		Rooms Room = RoomsService.getRoomById(id);

		model.addAttribute("name", Room.getName());
		model.addAttribute("capacity", Room.getCapacity());
		
		return "page/room/detail";
	}
}
