fun main() {
    val typePhoto = Photo("photo")
    val typeVideo = Video("video")
    val attachments = mapOf("photo" to typePhoto, "video" to typeVideo)
    val post1 = Post(text = "post 1", date = 12, likes = Likes(50, true), attachments = attachments)
    val post2 = Post(text = "post 2", date = 34, likes = Likes(23, true), attachments = attachments)
    val post3 = Post(text = "post 3", date = 78, likes = Likes(67, true), attachments = attachments)
    val post4 = Post(id = 2, text = "new post 2", date = 56, likes = Likes(45, true), attachments = attachments)
    val comment1 = Comment(date = 34, text = "com 1", attachments = attachments)
    val comment2 = Comment(date = 34, text = "com 2", attachments = attachments)
    WallService.add(post1)
    WallService.add(post2)
    WallService.add(post3)
    WallService.update(post4)
    WallService.createComment(2, comment1)
    WallService.createComment(3, comment2)
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
    val likes: Likes,
    val attachments: Map<String, Attachments>
)

data class Note(
    val id: Int = 0,
    val title: String = "",
    val date: Int,
    val text: String = "",
    val commentsCount: Int?= null,
    val comments: MutableList<Comment>,
    val readComments: Int?= null,
    val viewUrl: String?= null,
    val privacyUrl: String?= null,
    var canComment: Int?= null,
    val textWiki: String?= null
)

class NoteService{
    private val items: MutableList<Note> = mutableListOf<Note>()
    private var noteId = 0

    fun add(elem: Note): Note {
        noteId++
        items += elem.copy(id = noteId)
        return items.last()
    }

    fun createComment(noteId: Int, comment: Comment): Comment {
        var count: Int? = null
        for ((index, note) in items.withIndex()) {
            if (note.id == noteId) {
                note.comments += comment
                count = 1
            }
        }
        count ?: throw PostNotFoundException("id not found")
        return comment
    }

    fun delete(noteId: Int): Boolean {
        var count: Int? = null
        for ((index, note) in items.withIndex()) {
            if (note.id == noteId) {
                items.remove(note)
                count = 1
            }
        }
        count ?: throw PostNotFoundException("id not found")
        return true
    }

    fun deleteComment(noteId: Int, commentId: Int): Boolean {
        var count: Int? = null
        for ((index, note) in items.withIndex()) {
            if (note.id == noteId) {
                for ((index, comment) in note.comments.withIndex()){
                    if (comment.id == commentId){
                        comment.delete = true
                    }
                }
                count = 1
            }
        }
        count ?: throw PostNotFoundException("id not found")
        return true
    }

    fun edit(note: Note): Boolean {
        for ((index, origNote) in items.withIndex()) {
            if (origNote.id == note.id) {
                items[index] = note.copy()
                return true
            }
        }
        return false
    }

    fun editComment(noteId: Int, comment: Comment): Boolean {
        var count: Int? = null
        for ((index, note) in items.withIndex()) {
            if (note.id == noteId) {
                for ((index, origComment) in note.comments.withIndex()){
                    if (origComment.id == comment.id){
                        note.comments[index] = comment
                        return true
                    }
                }
            }
        }
        return false
    }

    fun get(): MutableList<Note> {
        return items
    }

    fun getByld(noteId: Int): Note {
        for ((index, note) in items.withIndex()) {
            if (note.id == noteId) {
                return note
            }
        }
        throw PostNotFoundException("id not found")
    }

    fun getComments(noteId: Int): MutableList<Comment> {
        for ((index, note) in items.withIndex()) {
            if (note.id == noteId) {
                return note.comments
            }
        }
        throw PostNotFoundException("id not found")
    }

    fun restoreComment(noteId: Int, commentId: Int): Boolean {
        var count: Int? = null
        for ((index, note) in items.withIndex()) {
            if (note.id == noteId) {
                for ((index, comment) in note.comments.withIndex()){
                    if (comment.id == commentId){
                        comment.delete = false
                    }
                }
                count = 1
            }
        }
        count ?: throw PostNotFoundException("id not found")
        return true
    }


    fun printAll() {
        for (note in items) {
            println(note)
            for (comment in note.comments) {
                println(comment)
            }
        }

    }


}
interface Attachments {
    val type: String
}

class Photo(override val type: String = "photo") : Attachments
class Video(override val type: String = "video") : Attachments
class Audio(override val type: String = "audio") : Attachments
class Doc(override val type: String = "doc") : Attachments


class PostNotFoundException(message: String) : RuntimeException(message)

class Likes(
    val count: Int,
    val userLikes: Boolean
)

data class Comment(
    val id: Int = 0,
    val fromId: Int = 0,
    val date: Int,
    val text: String = "",
    val replyToUser: Int? = null,
    val replyToComment: Int? = null,
    var delete : Boolean? = null,
    val attachments: Map<String, Attachments>
)

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var postId = 0

    fun add(post: Post): Post {
        postId++
        posts += post.copy(id = postId)
        return posts.last()
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        var count: Int? = null
        for ((index, post) in posts.withIndex()) {
            if (post.id == postId) {
                comments += comment
                count = 1
            }
        }
        count ?: throw PostNotFoundException("id not found")
        return comment
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
        for (comment in comments) {
            println(comment)
        }
    }

    fun clear() {
        posts = emptyArray()
        postId = 0
    }
}