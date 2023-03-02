package projects.repositories

import projects.extensions.toProject
import projects.models.Project
import projects.models.ProjectDbModel
import users.repositories.PanacheUserRepository
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class ProjectRepository(
    private var _panacheUserRepository: PanacheUserRepository,
    private var _panacheProjectRepository: PanacheProjectRepository,
) : IProjectRepository {

    @Transactional
    override fun add(project: Project): Project {
        return _panacheProjectRepository.add(
            ProjectDbModel(
                id = project.id,
                name = project.name,
                link = project.link,
                year = project.year,
                user = _panacheUserRepository.getById(project.userId)
            )
        ).toProject()
    }

    override fun getById(id: UUID): Project {
        val dbModel = _panacheProjectRepository.getById(id)

        return dbModel.toProject()
    }

    override fun getAllByUserId(id: UUID): List<Project> {
        val dbModels = _panacheProjectRepository.getAllByUserId(id)

        return dbModels.map { it.toProject() }
    }

    @Transactional
    override fun update(project: Project): Project {
        return _panacheProjectRepository.update(
            ProjectDbModel(
                id = project.id,
                name = project.name,
                link = project.link,
                year = project.year,
                user = _panacheUserRepository.getById(project.userId)
            )
        ).toProject()
    }

    @Transactional
    override fun removeById(id: UUID): Project {
        return _panacheProjectRepository.removeById(id).toProject()
    }
}