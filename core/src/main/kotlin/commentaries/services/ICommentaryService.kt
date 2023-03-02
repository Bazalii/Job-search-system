package commentaries.services

import commentaries.models.Commentary
import commentaries.models.CommentaryCreationModel
import java.util.*

interface ICommentaryService {
    fun add(commentaryCreationModel: CommentaryCreationModel): Commentary
    fun getById(id: UUID): Commentary
    fun getAllByUserId(id: UUID): List<Commentary>
    fun update(commentary: Commentary): Commentary
    fun removeById(id: UUID): Commentary
}