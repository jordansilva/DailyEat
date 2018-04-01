package app.jordansilva.data.mapper

interface RemoteMapper<in R, out D> {
    fun mapFromRemote(type: R): D
}