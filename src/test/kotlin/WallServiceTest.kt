import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val result = (WallService.add(Post(date = 24, text = "post 1", likes = Likes(455, true),attachments = emptyMap()))).id
        assertEquals(1, result)
    }

    @Test
    fun updateTrue() {
        WallService.add(Post(date = 24, text = "post 1", likes = Likes(455, true),attachments = emptyMap()))
        val result = (WallService.update(Post(id = 1, date = 34, text = "new post 1", likes = Likes(45, true),attachments = emptyMap())))
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        WallService.add(Post(date = 24, text = "post 1", likes = Likes(455, true),attachments = emptyMap()))
        val result = (WallService.update(Post(id = 4, date = 34, text = "new post 1", likes = Likes(467, false),attachments = emptyMap())))
        assertFalse(result)
    }

    @Test
    fun createCommentTrue() {
        val comment1 = Comment(date = 23,text = "com 1",attachments = emptyMap())
        WallService.add(Post(date = 24, text = "post 1", likes = Likes(455, true),attachments = emptyMap()))
        val result = WallService.createComment(1,comment1)
        assertEquals(comment1, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentNotFound() {
        val comment1 = Comment(date = 23,text = "com 1",attachments = emptyMap())
        WallService.add(Post(date = 24, text = "post 1", likes = Likes(455, true),attachments = emptyMap()))
        val result = WallService.createComment(4,comment1)
    }
}