import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;


public class NonEndangeredAnimalsTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animalInstantiatesCorrectly_true() {
        NonEndangeredAnimals testAnimal = new NonEndangeredAnimals("Lion", "young", "Okay");
        assertTrue(testAnimal instanceof NonEndangeredAnimals);
    }

    @Test
    public void getName_nonEndangeredAnimalInstantiatesWithName_Lion() {
        NonEndangeredAnimals testAnimal = new NonEndangeredAnimals("Lion", "young", "Okay");
        assertEquals("Lion", testAnimal.getName());
    }

    @Test
    public void getAge_nonEndangeredAnimalInstantiatesWithName_Lion() {
        NonEndangeredAnimals testAnimal = new NonEndangeredAnimals("Lion", "young", "Okay");
        assertEquals("young", testAnimal.getAge());
    }

    @Test
    public void getHealth_nonEndangeredAnimalInstantiatesWithName_Lion() {
        NonEndangeredAnimals testAnimal = new NonEndangeredAnimals("Lion", "young", "Okay");
        assertEquals("Okay", testAnimal.getHealth());
    }

    @Test
    public void getType_nonEndangeredAnimalInstantiatesWithType_endangered() {
        NonEndangeredAnimals testAnimal = new NonEndangeredAnimals("Rhino","young","healthy");
        assertEquals("non-endangered", testAnimal.getType());
    }

    @Test
    public void equals_returnsTrueIfNameIsSame_true() {
        NonEndangeredAnimals testAnimal = new NonEndangeredAnimals("Lion", "young","Okay");
        NonEndangeredAnimals anotherAnimal = new NonEndangeredAnimals("Lion", "young","Okay");
        assertEquals(testAnimal, anotherAnimal);

    }

    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        NonEndangeredAnimals testAnimal = new NonEndangeredAnimals("Lion", "young","Okay");
        testAnimal.save();
        assertEquals(NonEndangeredAnimals.all().get(0), testAnimal);

    }

    @Test
    public void all_ReturnsAllInstancesOfAnimal_true() {
        NonEndangeredAnimals testAnimal = new NonEndangeredAnimals("Lion", "young","Okay");
        NonEndangeredAnimals anotherAnimal = new NonEndangeredAnimals("Giraffe", "young","Okay");
        testAnimal.save();
        anotherAnimal.save();
        assertEquals(NonEndangeredAnimals.all().get(0), testAnimal);
        assertEquals(NonEndangeredAnimals.all().get(1), anotherAnimal);
    }

}