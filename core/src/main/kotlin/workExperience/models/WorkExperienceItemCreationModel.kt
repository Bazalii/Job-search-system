package workExperience.models

import java.time.LocalDate
import java.util.*

data class WorkExperienceItemCreationModel(
    var place: String,
    var position: String,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var userId: UUID,
)