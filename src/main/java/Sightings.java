import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Sightings implements SaveDelete {

    private String rangerName;
    private int animalId;
    private int id;
    private String location;
    private Timestamp sightingDate;



    public Sightings(String rangerName, int animalId, String location) {
        if (rangerName.equals("")) {
            throw new IllegalArgumentException("ranger name.");
        }
        this.rangerName = rangerName;
        this.animalId = animalId;
        this.location = location;
        this.sightingDate = new Timestamp(new Date().getTime());

    }

    public String getRangerName() {
        return rangerName;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getTimeSeen(){
        return String.format("%1$TD %1$TR", sightingDate);
    }

    public Timestamp getSightingDate() {
        return sightingDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sightings sighting = (Sightings) o;
        return getAnimalId() == sighting.getAnimalId() &&
                Objects.equals(getRangerName(), sighting.getRangerName()) &&
                Objects.equals(getLocation(), sighting.getLocation()) &&
                Objects.equals(getSightingDate(), sighting.getSightingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRangerName(), getAnimalId(), getLocation(), getSightingDate());
    }

    public static List<Sightings> all() {
        String sql = "SELECT * FROM sightings;";
        try (Connection con = Database.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Sightings.class);
        }
    }

    public static Sightings find(int id) {
        try (Connection con = Database.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id = :id;";
            Sightings sighting = con.createQuery(sql).addParameter("id", id).throwOnMappingFailure(false).executeAndFetchFirst(Sightings.class);
            return sighting;

        }
    }

    @Override
    public void save() {
        try (Connection con = Database.sql2o.open()) {
            String sql = "INSERT INTO sightings (rangerName, animalId, location, sightingDate) VALUES (:rangerName, :animalId, :location, :sightingDate);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("animalId", this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("sightingDate", this.sightingDate)
                    .executeUpdate()
                    .getKey();
        }catch (Sql2oException ex) {
            System.out.println("found "+ex);
        }

    }

    @Override
    public void delete() {
        try (Connection con = Database.sql2o.open()) {
            String sql = "DELETE FROM sightings WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }

}