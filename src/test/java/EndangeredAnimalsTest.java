import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class EndangeredAnimalsTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void endangeredAnimal_InstantiatesCorrectly_true() {
        EndangeredAnimals testAnimalEndangered = new EndangeredAnimals("Rhino","young","healthy");
        assertTrue(testAnimalEndangered instanceof EndangeredAnimals);

    }

    @Test
    public void getName_EndangeredAnimalInstantiatesWithName_Rhino() {
        EndangeredAnimals testAnimalEndangered = new EndangeredAnimals("Rhino","young","healthy");
        assertEquals("Rhino", testAnimalEndangered.getName());
    }

    @Test
    public void getAge_EndangeredAnimalInstantiatesWithName_Rhino() {
        EndangeredAnimals testAnimalEndangered = new EndangeredAnimals("Rhino","young","healthy");
        assertEquals("young", testAnimalEndangered.getAge());
    }

    @Test
    public void getHealth_EndangeredAnimalInstantiatesWithName_Rhino() {
        EndangeredAnimals testAnimalEndangered = new EndangeredAnimals("Rhino","young","healthy");
        assertEquals("healthy", testAnimalEndangered.getHealth());
    }

    @Test
    public void getType_EndangeredAnimalInstantiatesWithType_endangered() {
        EndangeredAnimals testAnimalEndangered = new EndangeredAnimals("Rhino","young","healthy");
        assertEquals("endangered", testAnimalEndangered.getType());
    }

    @Test
    public void equalsReturnsTrueIfNameIsSame_true() {
        EndangeredAnimals testAnimalEndangered = new EndangeredAnimals("Rhino","young","healthy");
        EndangeredAnimals otherAnimalEndangered = new EndangeredAnimals("Rhino","young","healthy");
        assertEquals(testAnimalEndangered, otherAnimalEndangered);

    }

    @Test
    public void save_returnsTrueIfDescriptionsAreSame_true() {
        EndangeredAnimals testAnimalEndangered = new EndangeredAnimals("Rhino", "healthy", "young");
        testAnimalEndangered.save();
        assertEquals(EndangeredAnimals.all().get(0), testAnimalEndangered);
    }

    @Test
    public void save_assignsIdToEndangeredAnimals() {
        EndangeredAnimals testAnimalEndangered = new EndangeredAnimals("Rhino", "healthy", "young");
        testAnimalEndangered.save();
        EndangeredAnimals savedEndangeredAnimal = EndangeredAnimals.all().get(0);
        assertEquals(savedEndangeredAnimal.getId(), testAnimalEndangered.getId());
    }

    @Test
    public void all_returnsAllInstancesOfEndangeredAnimals_() {
        EndangeredAnimals testAnimalEndangered = new EndangeredAnimals("Rhino", "healthy", "young");
        EndangeredAnimals otherAnimalEndangered = new EndangeredAnimals("Elephant", "healthy", "young");
        testAnimalEndangered.save();
        otherAnimalEndangered.save();
        assertEquals(EndangeredAnimals.all().get(0), testAnimalEndangered);
        assertEquals(EndangeredAnimals.all().get(1), otherAnimalEndangered);
    }
}