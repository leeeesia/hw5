fun main() {
    val typePhoto = Photo("photo")
    val typeVideo = Video("video")
    val attachments = mapOf("photo" to typePhoto, "video" to typeVideo)
    val post1 = Post(text = "post 1", date = 12, likes = likes(50, true), attachments = attachments)
    val post2 = Post(text = "post 2", date = 34, likes = likes(23, true), attachments = attachments)
    val post3 = Post(text = "post 3", date = 78, likes = likes(67, true), attachments = attachments)
    val post4 = Post(id = 2, text = "new post 2", date = 56, likes = likes(45, true), attachments = attachments)
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
    val createdBy: Int = 0,
    val date: Int,
    val text: String = "",
    val replyPostId: Boolean? = null,
    val friendsOnly: Boolean? = false,
    val postType: String? = "post",
    val canEdit: Boolean = false,
    val isPinned: Boolean? = null,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean? = null,
    val canPin: Boolean? = true,
    val postponedId: Int = 0,
    val likes: likes,
    val attachments : Map<String, Attachments>
)

interface Attachments{
    val type : String
}

class Photo (override val type: String = "photo" ) : Attachments
class Video (override val type: String = "video" ) : Attachments
class Audio (override val type: String = "audio" ) : Attachments
class Doc (override val type: String = "doc" ) : Attachments
class Note (override val type: String = "note" ) : Attachments

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