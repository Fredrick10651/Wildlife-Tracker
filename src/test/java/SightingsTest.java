import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SightingsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Sighting_InstantiatesCorrectly_true() {
        Sightings newSighting = new Sightings("Hiram",1,"Zone A");

        assertTrue(newSighting instanceof Sightings);
    }

    @Test
    public void getRangerName_InstantiatesCorrectlyWithRangerName_Hiram() {
        Sightings newSighting = new Sightings("Hiram",1,"Zone A");
        assertEquals("Hiram", newSighting.getRangerName());
    }

    @Test
    public void getAnimalId_InstantiatesCorrectlyWithAnimalID_1() {
        Sightings newSighting = new Sightings("Hiram",1,"Zone A");
        assertEquals(1, newSighting.getAnimalId());
    }

    @Test
    public void getLocation_InstantiatesCorrectlyWithLocation_ZoneA() {
        Sightings newSighting = new Sightings("Hiram",1,"Zone A");
        assertEquals("Zone A", newSighting.getLocation());
    }

    @Test
    public void equals_returnsTrueIfInstanceIsSame_true() {
        Sightings newSighting = new Sightings("Hiram",1,"Zone A");
        Sightings otherSighting = new Sightings("Hiram",1,"Zone A");
        assertTrue(newSighting.equals(otherSighting));

    }

    @Test
    public void save_successfullyAddsSightingToDatabase_List() {
        Sightings newSighting = new Sightings("Hiram",1,"Zone A");
        newSighting.save();
        assertEquals(Sightings.all().get(0), newSighting);
    }

    @Test
    public void save_assignsIdToSighting() {
        Sightings newSighting = new Sightings("Hiram",1,"Zone A");
        newSighting.save();
        Sightings savedSighting = Sightings.all().get(0);
        assertEquals(savedSighting.getId(), newSighting.getId());
    }

    @Test
    public void all_returnsAllInstancesOfSightings_true() {
        Sightings newSighting = new Sightings("Hiram",1,"Zone A");
        Sightings otherSighting = new Sightings("Mary",3,"Zone B");
        newSighting.save();
        otherSighting.save();
        assertEquals(true, Sightings.all().get(0).equals(newSighting));
        assertEquals(true, Sightings.all().get(1).equals(otherSighting));
    }

    @Test
    public void save_recordsTimeOfCreationInDatabase() {
        Sightings newSighting = new Sightings("Hiram",1,"Zone A");
        newSighting.save();
        Timestamp savedSighting = Sightings.find(newSighting.getId()).getSightingDate();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(rightNow.getDay(), savedSighting.getDay());
    }

    @Test
    public void findId_returnsSightingsId() {
        Sightings newSighting = new Sightings("Hiram",1,"Zone A");
        Sightings otherSighting = new Sightings("Mary",3,"Zone B");
        newSighting.save();
        otherSighting.save();
        assertEquals(Sightings.find(newSighting.getId()), newSighting);
        assertEquals(Sightings.find(otherSighting.getId()), otherSighting);
    }

}