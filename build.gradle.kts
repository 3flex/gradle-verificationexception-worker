val createFile by tasks.registering(CreateFile::class) {
    destination = layout.buildDirectory.file("file")
}

val createFileWorker by tasks.registering(CreateFileWorker::class) {
    destination = layout.buildDirectory.file("fileWorker")
}

tasks.register("processCreateFile") {
    inputs.file(createFile.map { it.destination })
    doLast {
        println("SUCCESS")
    }
}

tasks.register("processCreateFileWorker") {
    inputs.file(createFileWorker.map { it.destination })
    doLast {
        println("SUCCESS")
    }
}
