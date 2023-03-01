import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun add() {
        ChatService.add(1,Message("msg1",1))
        val result = ChatService.getChatList()[0]
        assertEquals("msg1", result)
    }

    @Test
    fun getChatList() {
        ChatService.add(1,Message("msg1",1))
        val result = ChatService.getChatList()[0]
        assertEquals("msg1", result)
    }

    @Test
    fun getMessageList() {
        ChatService.add(1,Message("msg1",1))
        val result = ChatService.getMessageList(1,5)[0].id
        assertEquals(1, result)
    }

    @Test
    fun deleteChat() {
        ChatService.add(1,Message("msg1",1))
        ChatService.deleteChat(1)
        val result = ChatService.getChatList()[0]
        assertEquals("msg1", result)
    }

    @Test
    fun deleteMessage() {
        ChatService.add(1,Message("msg1",1))
        ChatService.deleteMessage(1,1,"msg1")
        val result = ChatService.getChatList()[0]
        assertEquals("msg1", result)
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService.add(1,Message("msg1",1))
        val result = (ChatService.getUnreadChatsCount()).toString()
        assertEquals("[msg1]", result)
    }
}