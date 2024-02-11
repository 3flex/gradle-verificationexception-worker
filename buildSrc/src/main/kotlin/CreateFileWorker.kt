import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.VerificationException
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import org.gradle.workers.WorkerExecutor
import javax.inject.Inject


abstract class CreateFileWorker : DefaultTask() {
    @Inject
    abstract fun getWorkerExecutor(): WorkerExecutor

    @get:OutputFile
    abstract val destination: RegularFileProperty

    @TaskAction
    fun createHashes() {
        destination.get().asFile.writeText("output")

        val workQueue = getWorkerExecutor().noIsolation()
        workQueue.submit(CreateMD5Action::class.java) {}
    }
}


abstract class CreateMD5Action : WorkAction<MD5WorkParameters> {
    override fun execute() {
        throw VerificationException("verification exception")
    }
}

interface MD5WorkParameters : WorkParameters