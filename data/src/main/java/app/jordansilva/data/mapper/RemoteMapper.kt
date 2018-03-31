package app.jordansilva.data.mapper

interface RemoteMapper<R, D> {
    fun mapFromRemote(type: R): D
}