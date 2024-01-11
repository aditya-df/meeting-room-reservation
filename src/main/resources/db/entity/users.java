package db.entity;

public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean active;
}

interface PersonRepository extends Repository<users, Long> {

  Person save(Person person);

  Optional<Person> findById(long id);
}