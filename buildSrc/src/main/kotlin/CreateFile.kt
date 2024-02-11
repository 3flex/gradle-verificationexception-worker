import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.VerificationException

abstract class CreateFile : DefaultTask() {
    @get:OutputFile
    abstract val destination: RegularFileProperty

    @TaskAction
    fun createHashes() {
        destination.get().asFile.writeText("output")
        throw VerificationException("verification exception")
    }
}
