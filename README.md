# Prerequisites:
1. Docker
2. Maven (version high enough to build Java 17 program) (Optional)

# Setup

1. Clone git repository into your machine: `git clone https://github.com/KamilisS/cat-project.git`.
2. Generate executable `.jar` file using `mvn clean install` in the project root directory. Maven version to create this
project was `3.9.9` and Java version is 17. If `mvn clean install` fails due to version incompatibilities, you can download
generated `.jar` file from here: `https://drive.google.com/file/d/1f1p3oEjeYBILKrFuHMUB1EK2piij93ly/view?usp=sharing`.
After download, just put `.jar` file to `/target/` directory inside project, docker will take it from there.
3. Run `sudo docker compose up -d --build` command to launch both - application and database (PostgreSQL) containers. Note: `sudo` is
mandatory because of permissions put on the database data folder.
4. After images complete building and containers start, app is ready to be used. Database schema should be automatically created on startup.

# Program notes

1. You can open `http://localhost:8085/swagger-ui/index.html` and you will see all the endpoints, their
descriptions (request and response bodies) and a possibility to test each endpoint.
2. `POST`, `PUT`, and `DELETE` methods are secured by Spring Security using Basic authentication.
Use these credentials: `username: testuser; password: 123456`.
3. Note using `GET /cats` endpoint: `order` parameter will define sort field. For descending order add `-`before field name.
E.g. `&order=-dateOfBirth`. For ascending order, field name is without leading `-`, e.g. `&order=dateOfBirth`.
`page` count starts at 0.
4. Entity validations: `name` (not blank + longer than 1 character), `breed` (not blank + longer than 1 character),
`color` (not blank + longer than 2 characters), `dateOfBirth` (not blank, present day or a day in the past).
`age` will be calculated on the go, because this field is dynamic, meaning it will have to be changed each year, which is not
a good practice, when we have dateOfBirth and age can be easily calculated on-the-go.

If you have any questions regarding this program, feel free to contact me via email: gvt488@gmail.com.