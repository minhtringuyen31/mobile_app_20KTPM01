import DB from '../configs/db.js';
const ToppingRepository = {
  async create(category_id, name, price, checked) {
    const query = `INSERT INTO topping (category_id, name, price, checked) VALUES (?, ?, ?, ?)`;
    const values = [category_id, name, price, checked];
    try {
      DB.pool().query(query, values);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async update(id, category_id, name, price, checked) {
    const query = `UPDATE topping SET category_id=?, name=?, price=?, checked=? WHERE id=?`;
    const values = [category_id, name, price, checked, id];

    try {
      const [result] = await DB.pool().query(query, values);
      return result.affectedRows > 0;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async delete(id) {
    const query = `DELETE FROM topping WHERE id=?`;

    try {
      await DB.pool().query(query, [id]);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findAll() {
    const query = `SELECT * FROM topping`;

    try {
      const [rows] = await DB.pool().query(query);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findOneByID(id) {
    const query = `SELECT * FROM topping WHERE id = ?`;
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

export default ToppingRepository;
