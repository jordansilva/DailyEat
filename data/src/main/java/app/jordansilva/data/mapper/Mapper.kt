package app.jordansilva.data.mapper

interface Mapper<E, D> {

    fun mapFromDomain(type: E): D
    fun mapToDomain(type: D): E
}