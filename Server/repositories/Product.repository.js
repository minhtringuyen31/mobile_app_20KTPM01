import { query } from 'express';
import DB from '../configs/db.js';
const ProductRepository = {
  async countProduct() {
    const query = `SELECT COUNT(*) AS count FROM product`;
    try {
      const [result] = await DB.pool().query(query);
      return result[0].count;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async changeStatus(id, status) {
    const query = `UPDATE product SET status=? WHERE id=?`;
    const values = [status, id];
    try {
      const [result] = DB.pool().query(query, values);
      return result.affectedRows > 0;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async changeIsDisable(id, is_disable) {
    const query = `UPDATE product SET is_disable=? WHERE id=?`;
    const values = [is_disable, id];
    try {
      const [result] = DB.pool().query(query, values);
      return result.affectedRows > 0;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async create(
    name,
    description,
    size,
    price_S,
    price_M,
    price_L,
    image,
    status,
    category_id,
    update_date,
    release_date,
    sales
  ) {
    const query = `INSERT INTO product (name, description, size, price_S, price_M, price_L, image, status, category_id, update_date, release_date, sales) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?,?)`;
    const values = [
      name,
      description,
      size,
      price_S,
      price_M,
      price_L,
      image,
      status,
      category_id,
      update_date,
      release_date,
      sales,
    ];
    try {
      DB.pool().query(query, values);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async updateWithoutImage(
    id,
    name,
    description,
    size,
    price_S,
    price_M,
    price_L,
    category_id,
    update_date
  ) {
    const query = `UPDATE product SET name=?, description=?, size=?, price_S=?, price_M=? ,price_L=? ,category_id=?,update_date=? WHERE id=?`;
    const values = [
      name,
      description,
      size,
      price_S,
      price_M,
      price_L,
      category_id,
      update_date,
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
  async update(
    id,
    name,
    description,
    size,
    price_S,
    price_M,
    price_L,
    image,
    category_id,
    update_date
  ) {
    const query = `UPDATE product SET name=?, description=?, size=?, price_S=?, price_M=? ,price_L=? ,image=?,category_id=?,update_date=? WHERE id=?`;
    const values = [
      name,
      description,
      size,
      price_S,
      price_M,
      price_L,
      image,
      category_id,
      update_date,
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
    const query = `DELETE FROM product WHERE id=?`;

    try {
      await DB.pool().query(query, [id]);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findAll() {
    const query = `SELECT * FROM product ORDER BY sales DESC`;

    try {
      const [rows] = await DB.pool().query(query);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findOneByID(id) {
    const query = `SELECT * FROM product WHERE id = ?`;
    const value = [id];

    try {
      const [rows] = await DB.pool().query(query, value);
      return rows[0];
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async updateSales(id) {
    const queryOne = `SELECT * FROM product WHERE id = ?`;
    const [rows] = await DB.pool().query(queryOne, [id]);
    const sales = rows[0].sales + 1;
    const query = `UPDATE product set sales=? WHERE id = ?`;
    try {
      const [rows] = await DB.pool().query(query, [sales, id]);
      return rows[0];
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async findAllFavProduct(userId) {
    const query = `SELECT p.* FROM favorite_product AS fp JOIN product AS p ON fp.product_id = p.id WHERE fp.user_id = ?`;
    const value = [userId];

    try {
      const [rows] = await DB.pool().query(query, value);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async addNewFavProduct(userId, productId) {
    const query = `INSERT INTO favorite_product (user_id, product_id) VALUES (?, ?)`;
    const values = [userId, productId];
    try {
      DB.pool().query(query, values);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async removeFavProduct(userId, productId) {
    const query1 = `SELECT fp.id FROM favorite_product AS fp WHERE fp.user_id = ? AND fp.product_id = ? LIMIT 1`;
    const query = `DELETE FROM favorite_product WHERE id = ?`;
    const values = [userId, productId];
    try {
      const [row] = await DB.pool().query(query1, values);

      const id = row[0].id;
      await DB.pool().query(query, [id]);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async isExistedFavProduct(userId, productId) {
    const query = `SELECT fp.id FROM favorite_product AS fp WHERE fp.user_id = ? AND fp.product_id = ? LIMIT 1`;
    console.log('check ' + userId + productId);
    const values = [userId, productId];
    try {
      const [id, fields] = await DB.pool().query(query, values);
      console.log(id);
      return id.length > 0;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async findAllSales() {
    const query = `SELECT * FROM product WHERE sales != 0 ORDER BY sales DESC`;

    try {
      const [rows] = await DB.pool().query(query);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async getRating(productId) {
    const query = `SELECT avg(rating.score) FROM rating WHERE rating.product_id = ?`;
    try {
      const [rows] = await DB.pool().query(query, [productId]);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
};

export default ProductRepository;
