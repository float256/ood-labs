package model.shape

import io.reactivex.rxjava3.disposables.Disposable
import model.shape.style.Frame
import model.shape.style.ShapeStyle

interface Shape {
    val frame: Frame
    val style: ShapeStyle

    fun doOnStyleChange(func: (ShapeStyle) -> Unit): Disposable
    fun doOnFrameChange(func: (Frame) -> Unit): Disposable
}