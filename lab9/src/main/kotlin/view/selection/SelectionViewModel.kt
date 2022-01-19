package view.selection

import androidx.compose.runtime.mutableStateOf
import application.SelectionAppModel

class SelectionViewModel(
    selectionAppModel: SelectionAppModel
) {
    var frame = mutableStateOf(selectionAppModel.selectedShape?.frame)
        private set

    init {
        selectionAppModel.doOnSelectionChange {
            frame.value = it?.frame
        }
    }

    fun isShowSelection(): Boolean {
        return frame.value != null
    }
}