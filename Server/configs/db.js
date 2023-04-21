import mysql2 from 'mysql2';
import dotenv from 'dotenv'

dotenv.config()
console.log(process.env.WIN_PORT)
// moi nguoi khong chay duoc thi kiem tra lai pass nha. Cua Thai Duong pass la '' 
const _connection = mysql2.createPool({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'mobile_coffee',
  port: process.env.WIN_PORT,
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
