package observer

interface Observer<T> {
    fun update(observableSubject: Subject<T>, newData:T)
}