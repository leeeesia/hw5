fun main() {
    val post1 = Post(text = "post 1", date = 12, likes = likes(50, true))
    val post2 = Post(text = "post 2", date = 34, likes = likes(23, true))
    val post3 = Post(text = "post 3", date = 78, likes = likes(67, true))
    val post4 = Post(id = 2, text = "new post 2", date = 56, likes = likes(45, true))
    WallService.add(post1)
    WallService.add(post2)
    WallService.add(post3)
    WallService.update(post4)
    WallService.printAll()
}

data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val date: Int,
    val text: String = "",
    val friendsOnly: Boolean = false,
    val postType: String = "post",
    val isPinned: Boolean = false,
    val isFavorite: Boolean = false,
    val canPin: Boolean = true,
    val likes: likes
)

class likes(
    val count: Int,
    val userLikes: Boolean
)

object WallService {
    private var posts = emptyArray<Post>()
    private var postId = 0

    fun add(post: Post): Post {
        postId++
        posts += post.copy(id = postId)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, origPost) in posts.withIndex()) {
            if (origPost.id == post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }

    fun printAll() {
        for (post in posts) {
            println(post)
        }
    }

    fun clear() {
        posts = emptyArray()
        postId = 0
    }
}