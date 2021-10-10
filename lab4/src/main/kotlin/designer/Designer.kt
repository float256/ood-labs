package designer

import draft.PictureDraft
import java.io.InputStream

interface Designer {
    fun createDraft(inputStream: InputStream): PictureDraft
}