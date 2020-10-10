package fr.nansty.starwarsapi.people

data class People(var id: Long, var name: String) {

    constructor(name: String) : this(-1,name)
}