package painter

import canvas.Canvas
import draft.PictureDraft

class Painter {
    fun drawPicture(draft: PictureDraft, canvas: Canvas) {
        draft.shapes.forEach { it.draw(canvas) }
    }
}