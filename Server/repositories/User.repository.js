import DB from '../configs/db.js';
import crypto from 'crypto';
import bcrypt from 'bcrypt';
import CartServices from '../services/Cart.service.js';
const UserRepository = {
  async countUser() {
    const query = `SELECT COUNT(*) AS count FROM user WHERE role = '0'`;
    try {
      const [result] = await DB.pool().query(query);
      return result[0].count;
    } catch (error) {
      console.log(error);
      return false;
    }
  },
  async changeIsDisable(id, is_disable) {
    const query = `UPDATE user SET is_disable=? WHERE id=?`;
    const values = [is_disable, id];
    try {
      const [result] = await DB.pool().query(query, values);
      return result.affectedRows > 0;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async create(
    name,
    gender,
    email,
    phone,
    password,
    date_of_birth,
    address,
    avatar,
    role,
    is_disable
  ) {
    const query = `INSERT INTO user (name, gender,email,phone,password, date_of_birth, address, avatar, role,is_disable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`;
    const values = [
      name,
      gender,
      email,
      phone,
      password,
      date_of_birth,
      address,
      avatar,
      role,
      is_disable,
    ];
    try {
      const [result] = await DB.pool().query(query, values);
      const insertedId = result.insertId;
      const [userResult] = await DB.pool().query(
        `SELECT * FROM user WHERE id = ?`,
        [insertedId]
      );
      const insertedUser = userResult[0];
      return insertedUser;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async update(
    id,
    name,
    gender,
    email,
    phone,
    password,
    date_of_birth,
    address,
    avatar,
    role,
    is_disable
  ) {
    const query = `UPDATE user SET name=?, gender=?, email=?, phone=?, password=?, date_of_birth=?, address=?, avatar=?, role=?, is_disable=? WHERE id=?`;
    const values = [
      name,
      gender,
      email,
      phone,
      password,
      date_of_birth,
      address,
      avatar,
      role,
      is_disable,
      id,
    ];

    try {
      const [result] = await DB.pool().query(query, values);
      return result.affectedRows > 0;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async delete(id) {
    const query = `DELETE FROM user WHERE id=?`;
    try {
      await DB.pool().query(query, [id]);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findAll() {
    const query = `SELECT * FROM user WHERE role = '0'`;

    try {
      const [rows] = await DB.pool().query(query);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findOneByID(id) {
    const query = `SELECT * FROM user WHERE id = ?`;
    const value = [id];

    try {
      const [rows] = await DB.pool().query(query, value);
      return rows[0];
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  ///
  async findOneByPhone(phone) {
    const query = `SELECT * FROM user WHERE phone = ?`;
    const value = [phone];

    try {
      const [rows] = await DB.pool().query(query, value);

      return rows[0];
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findOneByEmail(email) {
    const query = `SELECT * FROM user WHERE email = ?`;
    const value = [email];

    try {
      const [rows] = await DB.pool().query(query, value);

      return rows[0];
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async signup(email, password) {
    const query = `INSERT INTO user (email,password,name) VALUES (?, ?,?)`;
    const chars =
      'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    const randomBytes = crypto.randomBytes(8);
    const result = new Array(8);
    for (let i = 0; i < 8; i++) {
      result[i] = chars[randomBytes[i] % 8];
    }
    const name = result.join('');
    console.log(name);
    const values = [email, password, name];
    try {
      const [result] = await DB.pool().query(query, values);
      const insertedId = result.insertId;
      const [userResult] = await DB.pool().query(
        `SELECT * FROM user WHERE id = ?`,
        [insertedId]
      );
      const insertedUser = userResult[0];
      await CartServices.create(insertedUser.id);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async getPass(email) {
    const query = `SELECT password FROM USER WHERE email=?`;
    const value = [email];

    try {
      const [rows] = await DB.pool().query(query, value);

      return rows[0];
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async setPass(email) {
    const query = `UPDATE user SET password=? WHERE email=?`;

    const pass = '12345';
    try {
      const password = await bcrypt.hash(pass, 10);
      const values = [password, email];
      console.log(values);
      try {
        const [result] = await DB.pool().query(query, values);
        if (result.affectedRows > 0) {
          return true;
        } else {
          return false;
        }
      } catch (error) {
        console.error(error);
        return false;
      }
    } catch (error) {
      console.error(error);
    }
  },
  async checkOTP(email, otp) {
    const query = `SELECT otp FROM user WHERE email = ?`;
    const value = [email, otp];

    try {
      const [rows] = await DB.pool().query(query, value);

      return rows[0];
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async setOTP(email, otp) {
    const query = `UPDATE user SET otp=? WHERE email like ?`;
    const values = [email, otp];
    console.log(values);
    try {
      const [result] = await DB.pool().query(query, values);
      console.log('here' + result);

      if (result.affectedRows > 0) {
        return true;
      } else {
        return false;
      }
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async changepass(id, password) {
    const query = 'UPDATE user SET password=? WHERE id=?';
    const values = [password, id];
    try {
      const [result] = await DB.pool().query(query, values);
      if (result.affectedRows > 0) {
        return true;
      } else {
        return false;
      }
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async editprofile(id, name, email, gender, date_of_birth, address) {
    const query = `UPDATE user SET name=?, gender=?, email=?, date_of_birth=?, address=? WHERE id=?`;
    const values = [name, gender, email, date_of_birth, address, id];

    try {
      const [result] = await DB.pool().query(query, values);
      if (result.affectedRows > 0) {
        return true;
      } else {
        return false;
      }
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async saveTokenFireBase(user_id, token) {
    const query = `INSERT INTO token_firebase (user_id,token) VALUES (?, ?)`;
    const values = [user_id, token];

    try {
      const [result] = await DB.pool().query(query, values);
      const insertedId = result.insertId;
      const [userResult] = await DB.pool().query(
        `SELECT * FROM token_firebase WHERE id = ?`,
        [insertedId]
      );
      const insertedUser = userResult[0];
      return insertedUser;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async findAllTokenFireBase() {
    const query = `SELECT * FROM token_firebase`;
    try {
      console.log('query,query');
      const [rows] = await DB.pool().query(query);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async findOneTokenFireBase(id) {
    const query = `SELECT * FROM token_firebase WHERE id = ?`;
    const values = [id];
    try {
      const [rows] = await DB.pool().query(query, values);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async findOneTokenByUserID(id) {
    const query = `SELECT * FROM token_firebase WHERE user_id = ?`;

    const values = [id];
    try {
      const [result] = await DB.pool().query(query, values);
      return result[0];
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async updateTokenFireBase(newtoken, id) {
    const query = `UPDATE token_firebase SET token=?  WHERE user_id=?`;
    const values = [newtoken, id];
    try {
      const [result] = await DB.pool().query(query, values);
      const insertedId = result.insertId;
      const [userResult] = await DB.pool().query(
        `SELECT * FROM token_firebase WHERE id = ?`,
        [insertedId]
      );
      const insertedUser = userResult[0];
      return insertedUser;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
};

export default UserRepository;
