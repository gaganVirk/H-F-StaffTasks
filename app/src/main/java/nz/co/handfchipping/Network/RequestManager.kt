package nz.co.handfchipping.Network

import android.content.Context
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import nz.co.handfchipping.Database.RequirementsDatabase
import nz.co.handfchipping.Models.Project
import nz.co.handfchipping.Repositories.ProjectRepository
import nz.co.handfchipping.ViewModels.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit


class RequestManager(context: Context) {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor(context))
        .callTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    @ImplicitReflectionSerializer
    suspend
    fun sync(context: Context) {
        val projectDao = RequirementsDatabase.getDatabase(context).proejctDao()
        val projectRepository = ProjectRepository(projectDao)

        val projects = projectRepository.getUnSyncedProjects().map { ProjectPost(it) }

        var syncPost = SyncPost(projects,  emptyList<CategoryPost>(), emptyList<SubcategoryPost>(), emptyList<TaskPost>(), null)

        var body = Json.stringify(SyncPost.serializer(), syncPost).toRequestBody("application/json; charset=utf-8".toMediaType())

        val response = okHttpClient.newCall(Request.Builder().url("http://hfchipping.azurewebsites.net/api/Sync").post(body).build()).execute()

        if(!response.isSuccessful) {
            return
        }

        val syncGet = Json.parse(SyncGet.serializer(), response.body!!.string())

        syncGet.projects.forEach {
            var project = projectRepository.getProject(it.id)

            if(project == null) {
                project = Project(it)
                projectRepository.insert(project)
            } else {
                project.update(it)
                projectRepository.update(project)
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: RequestManager? = null
        fun getInstance(context: Context): RequestManager {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = RequestManager(context)
                INSTANCE = instance
                return instance
            }
        }
    }
}