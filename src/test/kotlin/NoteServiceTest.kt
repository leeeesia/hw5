import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun add() {
        val noteService = NoteService()
        val note1 = Note(date = 45, text = "note 1", comments = mutableListOf())
        val result = (noteService.add(note1)).id
        assertEquals(1, result)
    }

    @Test
    fun createComment() {
        val noteService = NoteService()
        val note1 = Note(date = 45, text = "note 1", comments = mutableListOf())
        noteService.add(note1)
        val comment1 = Comment(date = 23,text = "com 1",attachments = emptyMap())
        val result = noteService.createComment(1,comment1)
        assertEquals(comment1, result)
    }


    fun delete() {
        val noteService = NoteService()
        val note1 = Note(id = 1, date = 45, text = "note 1", comments = mutableListOf())
        noteService.add(note1)
        val result = noteService.delete(1)
        assertEquals(true, result)
    }

    @Test
    fun deleteComment() {
        val noteService = NoteService()
        val note1 = Note(date = 45, text = "note 1", comments = mutableListOf())
        noteService.add(note1)
        val comment1 = Comment(id = 3, date = 23,text = "com 1",attachments = emptyMap())
        noteService.createComment(1,comment1)
        val result = noteService.deleteComment(1,3)
        assertEquals(true, result)
    }

    @Test
    fun edit() {
        val noteService = NoteService()
        val note1 = Note(date = 45, text = "note 1", comments = mutableListOf())
        val note2 = Note(id = 1, date = 45, text = "note 1", comments = mutableListOf())
        noteService.add(note1)
        val result = noteService.edit(note2)
        assertEquals(true, result)
    }

    @Test
    fun editComment() {
        val noteService = NoteService()
        val note1 = Note(date = 45, text = "note 1", comments = mutableListOf())
        noteService.add(note1)
        val comment1 = Comment(id = 3, date = 23,text = "com 1",attachments = emptyMap())
        val comment2 = Comment(id = 3, date = 23,text = "com 3",attachments = emptyMap())
        noteService.createComment(1,comment1)
        val result = noteService.editComment(1, comment1)
        assertEquals(true, result)
    }

    @Test
    fun get() {
        val noteService = NoteService()
        val note1 = Note(date = 45, text = "note 1", comments = mutableListOf())
        noteService.add(note1)
        val result = noteService.get()[0].id
        assertEquals(1, result)
    }

    @Test
    fun getByld() {
        val noteService = NoteService()
        val note1 = Note(date = 45, text = "note 1", comments = mutableListOf())
        noteService.add(note1)
        val result = noteService.getByld(1).id
        assertEquals(1, result)
    }

    @Test
    fun getComments() {
        val noteService = NoteService()
        val comments1 = mutableListOf<Comment>()
        val note1 = Note(date = 45, text = "note 1", comments = comments1)
        noteService.add(note1)
        val comment1 = Comment(id = 3, date = 23,text = "com 1",attachments = emptyMap())
        noteService.createComment(1,comment1)
        val result = noteService.getComments(1)
        assertEquals(comments1, result)
    }

    @Test
    fun restoreComment() {
        val noteService = NoteService()
        val comments1 = mutableListOf<Comment>()
        val note1 = Note(date = 45, text = "note 1", comments = comments1)
        noteService.add(note1)
        val comment1 = Comment(id = 3, date = 23,text = "com 1",attachments = emptyMap())
        noteService.createComment(1,comment1)
        val result = noteService.restoreComment(1, 3)
        assertEquals(true, result)
    }
}