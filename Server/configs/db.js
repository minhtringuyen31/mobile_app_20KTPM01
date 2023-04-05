import mysql2 from 'mysql2';
console.log(process.env.PORT_WIN)
const _connection = mysql2.createPool({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'mobile_coffee',
  port: 3306,
});
const DB = {
  pool() {
    return _connection.promise();
  },
  connection() {
    _connection.getConnection(function (error) {
      if (error) {
        console.error('Error connecting to MySQL database: ' + error.stack);
        return;
      }
      console.log(
        'Connected to MySQL database with connection ID ' + _connection.threadId
      );
    });
  },
};

export default DB;
