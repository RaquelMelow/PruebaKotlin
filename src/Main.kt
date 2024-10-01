fun main() {
    val dog = Dog(1, "Tobby", 300.0, "Labrador")
    val cat = Cat(2, "Kitty", 200.0, "Black")
    val bird = Bird(3, "Pio", 10.0, "Pio-Pio")

    val petShop = PetShop(mutableListOf(dog, cat, bird))

    println("Animales iniciales en la tienda:")
    petShop.listAnimals()

    val newCat = Cat(4,"Mia", 250.0, "Orange")
    petShop.addAnimal(newCat)

    println("\nDespués de agregar un nuevo gato")
    petShop.listAnimals()

    petShop.removeAnimal(2)

    println("\nDespués de eliminar un animal:")
    petShop.listAnimals()

    val foundCat = petShop.findAnimal(4)?.name
    println("\nSe busca el animal número 4 que es ${foundCat}")

    val totalValue = petShop.calculateTotalValue()
    println("\nLa suma total de los animales serian ${totalValue}")

    val filteredAnimals = petShop.filterAnimal("Dog").size
    println("\nCantidad de animales por la especie 'Dog' $filteredAnimals")

    val animalNamesMap = petShop.mapAllAnimalsNames()
    println("\nMapa de nombres de animales:")
    animalNamesMap.forEach { (name, animals) ->
        println("Nombre: $name, Especie: ${animals.joinToString { it.species }}")
    }

    val aniamlsPrice = petShop.calculateMediaAnimals()
    println("\nEl precio promedio de los animales es: $aniamlsPrice")
}

open class Animal (val id: Int, val name: String, val species: String, val price: Double) {

}

class Dog(id: Int, name: String, price: Double, val breed: String ) : Animal(id, name, "Dog", price) {

}

class Cat(id: Int, name: String, price: Double,  val colorCat: String) : Animal(id, name, "Cat", price) {

}

class Bird(id: Int, name: String, price: Double, val songType: String) : Animal(id, name, "Bird", price) {

}

class PetShop(val animals: MutableList<Animal>) {

    fun addAnimal(animal: Animal) {
        animals.add(animal)
    }

    fun removeAnimal(id: Int) {
        animals.removeIf { animal -> animal.id ==id }
    }

    fun findAnimal(id: Int): Animal? {
        return animals.find { it.id == id }
    }

    fun calculateTotalValue(): Double {
        return animals.sumOf { it.price }
    }

    fun listAnimals() {
        for (animal in animals) {
            when (animal) {
                is Dog -> println("ID: ${animal.id}, Nombre: ${animal.name}, Especie: ${animal.species}, Precio: ${animal.price}, Raza: ${animal.breed}")
                is Cat -> println("ID: ${animal.id}, Nombre: ${animal.name}, Especie: ${animal.species}, Precio: ${animal.price}, Color de pelaje: ${animal.colorCat}")
                is Bird -> println("ID: ${animal.id}, Nombre: ${animal.name}, Especie: ${animal.species}, Precio: ${animal.price}, Tipo de canto: ${animal.songType}")
            }
        }
    }

    fun filterAnimal(speciesFilter: String): List<Animal> {
        return animals.filter { it.species.equals(speciesFilter) }
    }

    fun mapAllAnimalsNames(): MutableMap<String, MutableList<Animal>> {
        val animalMap = mutableMapOf<String, MutableList<Animal>>()

        for (animal in animals) {
            animalMap.getOrPut(animal.name) { mutableListOf()}.add(animal)
        }
        return animalMap
    }

    fun calculateMediaAnimals(): Double {
        val totalPrice = animals.map { it.price }.reduce { acc, price -> acc + price}
        return totalPrice / animals.size
    }
}