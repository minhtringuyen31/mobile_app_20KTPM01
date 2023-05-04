import DB from '../configs/db.js';
const OrderProductRepository = {
  async create(note, order_id, product_id, quantity, price) {
    const query = `INSERT INTO order_product (note, order_id, product_id, quantity,price) VALUES (?, ?, ?, ?, ?)`;
    const values = [note, order_id, product_id, quantity, price];
    try {
      DB.pool().query(query, values);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async update(id, note, order_id, product_id, quantity, price) {
    const query = `UPDATE order_product SET note=?, order_id=?, product_id=?, quantity=?, price=? WHERE id=?`;
    const values = [note, order_id, product_id, quantity, price, id];
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
    const query = `DELETE FROM order_product WHERE id=?`;
    try {
      await DB.pool().query(query, [id]);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async findAll() {
    const query = `SELECT * FROM order_product`;
    try {
      const [rows] = await DB.pool().query(query);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async findOneByID(id) {
    const query = `SELECT * FROM order_product WHERE id = ?`;
    const value = [id];
    try {
      const [rows] = await DB.pool().query(query, value);
      return rows[0];
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async findAllByOrderID(order_id) {
    const query = `SELECT * FROM order_product WHERE order_id = ?`;
    const value = [order_id];
    try {
      const [rows] = await DB.pool().query(query, value);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
};

export default OrderProductRepository;
