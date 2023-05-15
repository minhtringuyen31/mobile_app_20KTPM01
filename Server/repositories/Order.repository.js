import DB from '../configs/db.js';
const OrderRepository = {
  async changeStatus(id, status) {
    const query = `UPDATE orders SET status=? WHERE id=?`;
    const values = [status, id];
    try {
      const [result] = await DB.pool().query(query, values);
      return result.affectedRows > 0;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async create(user_id, order_date, shipping_address, total, status, promotion_id, payment_method_id) {
    const query = `INSERT INTO orders (user_id, order_date, shipping_address, total, status, promotion_id, payment_method_id) VALUES (?, ?, ?, ?, ?,?,?)`;
    const values = [user_id, order_date, shipping_address, total, status, promotion_id, payment_method_id];
    try {

      const [result] = await DB.pool().query(query, values);
      const insertedId = result.insertId;
      const [ordersResult] = await DB.pool().query(`SELECT * FROM orders WHERE id = ?`, [insertedId]);
      const insertedOrder = ordersResult[0];
      return insertedOrder;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async update(id, user_id, order_date, shipping_address, total, status, promotion_id, payment_method_id) {
    const query = `UPDATE orders SET user_id=?, order_date=?, shipping_address=?, total=?, status=?,promotion_id=?,payment_method_id=? WHERE id=?`;
    const values = [user_id, order_date, shipping_address, total, status, promotion_id, payment_method_id, id];

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
  async delete(id) {
    const query = `DELETE FROM orders WHERE id=?`;

    try {
      await DB.pool().query(query, [id]);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findAll() {
    const query = `SELECT * FROM orders`;

    try {
      const [rows] = await DB.pool().query(query);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findByUserId(userId) {
    const query = `SELECT * FROM orders WHERE user_id = ?`;
    const value = [userId];

    try {
      const [rows] = await DB.pool().query(query, value);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async changeStatus(id, status) {
    const query = `UPDATE orders SET status=? WHERE id=?`;
    const values = [status, id];
    try {
      const [result] = await DB.pool().query(query, values);
      return result.affectedRows > 0;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async create(
    user_id,
    order_date,
    shipping_address,
    total,
    status,
    promotion_id,
    payment_method_id
  ) {
    const query = `INSERT INTO orders (user_id, order_date, shipping_address, total, status, promotion_id, payment_method_id) VALUES (?, ?, ?, ?, ?,?,?)`;
    const values = [
      user_id,
      order_date,
      shipping_address,
      total,
      status,
      promotion_id,
      payment_method_id,
    ];
    try {
      const [result] = await DB.pool().query(query, values);
      const insertedId = result.insertId;
      const [ordersResult] = await DB.pool().query(
        `SELECT * FROM orders WHERE id = ?`,
        [insertedId]
      );
      const insertedOrder = ordersResult[0];
      return insertedOrder;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async update(
    id,
    user_id,
    order_date,
    shipping_address,
    total,
    status,
    promotion_id,
    payment_method_id
  ) {
    const query = `UPDATE orders SET user_id=?, order_date=?, shipping_address=?, total=?, status=?,promotion_id=?,payment_method_id=? WHERE id=?`;
    const values = [
      user_id,
      order_date,
      shipping_address,
      total,
      status,
      promotion_id,
      payment_method_id,
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
    const query = `DELETE FROM orders WHERE id=?`;

    try {
      await DB.pool().query(query, [id]);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findAll() {
    const query = `SELECT * FROM orders`;

    try {
      const [rows] = await DB.pool().query(query);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findOneByID(id) {
    const query = `SELECT * FROM orders WHERE id = ?`;
    const value = [id];

    try {
      const [rows] = await DB.pool().query(query, value);
      return rows[0];
    } catch (error) {
      console.error(error);
      return false;
    }
  },



};

export default OrderRepository;
