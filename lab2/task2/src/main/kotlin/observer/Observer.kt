package observer

interface Observer<T> {
    fun update(newData:T)
}