import org.sql2o.Connection;

import java.util.List;


public class EndangeredAnimals extends Animal implements SaveDelete {

    public static final String ANIMAL_TYPE = "endangered";


    public EndangeredAnimals(String name, String age, String health) {
        this.name = name;
        this.age = age;
        this.health = health;
        type = ANIMAL_TYPE;

    }

    public static List<EndangeredAnimals> all() {
        String sql = "SELECT * FROM animals WHERE type=:type;";
        try (Connection con = Database.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type", ANIMAL_TYPE)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimals.class);

        }
    }

    public static List<EndangeredAnimals> allAnimals() {
        String sql = "SELECT * FROM animals;";
        try (Connection con = Database.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimals.class);
        }
    }

    public static EndangeredAnimals find(int id) {
        try (Connection con = Database.sql2o.open()) {
            String sql = "SELECT * FROM animals where id = :id;";

            EndangeredAnimals animal = con.createQuery(sql).addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimals.class);
            return animal;

        }
    }

    @Override
    public void save() {
        try (Connection con = Database.sql2o.open()) {
            String sql = "INSERT INTO animals (name, age, health, type) VALUES (:name, :age,:health, :type)";
            this.id = (int) con.createQuery(sql, true).addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public void delete() {
        try (Connection con = Database.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE id = :id;";
            con.createQuery(sql).addParameter("id", this.id).executeUpdate();

        }

    }

}
