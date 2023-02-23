package hh5.twogaether.domain.chat.controller;

import hh5.twogaether.domain.chat.dto.ChatRoomCreateRequestDto;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.service.ChatRoomService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms() {
        return "/chat/room";
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestBody ChatRoomCreateRequestDto createRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
         return chatRoomService.createChatRoom(createRequest,userDetails);
    }


    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> chatRoomList(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return chatRoomService.findAllRoom(userDetailsImpl);
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomService.findRoomById(roomId);
    }

//    @GetMapping("/rooms/{roomId}")
//    @ResponseBody
//    public List<ChatRoom> myChatRoom(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,@PathVariable String roomId) {
//        return chatRoomService.(userDetailsImpl,roomId);
//    }


//    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }

    // 특정 채팅방 조회


}