import mysql2 from "mysql2"
const _connection = mysql2.createPool({
    host: 'localhost',
    user: 'root',
    password: 'root',
    database: 'mobile_coffee',
    port: 8889,
})
const DB = {

    pool() {
        return _connection.promise()

    },
    connection() {
        _connection.connect(function (error) {
            if (error) {
                console.error('Error connecting to MySQL database: ' + error.stack);
                return;
            }

            console.log('Connected to MySQL database with connection ID ' + _connection.threadId);

        });
    }

}

export default DB;
