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
        val result = (WallService.add(Post(date = 24, text = "post 1", likes = likes(455, true)))).id
        assertEquals(1, result)
    }

    @Test
    fun updateTrue() {
        WallService.add(Post(date = 24, text = "post 1", likes = likes(455, true)))
        val result = (WallService.update(Post(id = 1, date = 34, text = "new post 1", likes = likes(45, true))))
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        WallService.add(Post(date = 24, text = "post 1", likes = likes(455, true)))
        val result = (WallService.update(Post(id = 4, date = 34, text = "new post 1", likes = likes(467, false))))
        assertFalse(result)
    }
}