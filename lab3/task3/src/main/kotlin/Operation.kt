enum class Operation(val commandName: String) {
    Encrypt("--encrypt"),
    Decrypt("--decrypt"),
    Compress("--compress"),
    Decompress("--decompress")
}