package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.repository.ChatMessageRepository;
import hh5.twogaether.domain.chat.repository.ChatRoomRepository;
import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private Map<String, ChatRoom> chatRoomMap;
    private final UserRepository userRepository;
    private final SimpMessageSendingOperations messagingTemplate;
    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

//    public void inviteUser(ChatMessage message){
//        ChatMsg msg = chatMsgRepository.fi
//        if (ChatMessage.MessageType.CHAT.equals(message.getType()))
//            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//        chatMsgRepository.save(message);
//    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom() {
        ChatRoom chatRoom = ChatRoom.create();
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

}