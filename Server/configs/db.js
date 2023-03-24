import mysql from "mysql2"

const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'root',
    database: 'mobile_coffee',
    port: 8889,
});

const db = () => {
    connection.connect(function (error) {
        if (error) {
            console.error('Error connecting to MySQL database: ' + error.stack);
            return;
        }

        console.log('Connected to MySQL database with connection ID ' + connection.threadId);

    });
}
export default db
