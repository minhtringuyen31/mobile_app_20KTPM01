import DB from '../configs/db.js';
const RatingRepository = {
  async countRating() {
    const query = `SELECT COUNT(*) AS count FROM rating`;
    try {
      const [result] = await DB.pool().query(query);
      return result[0].count;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async changeIsDisable(id, is_disable) {
    const query = `UPDATE rating SET is_disable=? WHERE id=?`;
    const values = [is_disable, id];
    try {
      const [result] = await DB.pool().query(query, values);
      return result.affectedRows > 0;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async create(user_id, user_name, user_image, product_id, score, comment) {
    const query = `INSERT INTO rating (user_id, user_name, user_image, product_id, score, comment) VALUES (?, ?, ?, ?, ?, ?)`;
    const values = [user_id, user_name, user_image, product_id, score, comment];
    try {
      DB.pool().query(query, values);
      console.log('rating here');
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async update(id, user_id, product_id, score, comment, create_at) {
    const query = `UPDATE rating SET user_id=?, product_id=?, score=?, comment=?, create_at=? WHERE id=?`;
    const values = [user_id, product_id, score, comment, create_at, id];

    try {
      const [result] = await DB.pool().query(query, values);
      return result.affectedRows > 0;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async delete(id) {
    const query = `DELETE FROM rating WHERE id=?`;

    try {
      await DB.pool().query(query, [id]);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findAll() {
    const query = `SELECT * FROM rating ORDER BY id DESC`;

    try {
      const [rows] = await DB.pool().query(query);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findRatingByProductID(id) {
    const query = `SELECT * FROM rating WHERE product_id = ?`;
    const value = [id];

    try {
      const [rows] = await DB.pool().query(query, value);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
};

export default RatingRepository;
