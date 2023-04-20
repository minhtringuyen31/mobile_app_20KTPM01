import DB from '../configs/db.js';
const ProductRepository = {
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
    note,
    image,
    status,
    category_id,
    update_date,
    release_date,
    sales
  ) {
    const query = `INSERT INTO product (name, description, size, price_S, price_M, price_L, note, image, status, category_id, update_date, release_date, sales) VALUES (?, ?, ?, ?, ?,?,?, ?, ?, ?, ?,?)`;
    const values = [
      name,
      description,
      size,
      price_S,
      price_M,
      price_L,
      note,
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
  async update(
    id,
    name,
    description,
    size,
    price_S,
    price_M,
    price_L,
    note,
    image,
    status,
    category_id,
    update_date,
    release_date,
    sales
  ) {
    const query = `UPDATE product SET name=?, description=?, size=?, price_S=?, price_M=? ,price_L=?, note=? ,image=?,status=?,category_id=?,update_date=?,release_date=?,sales=? WHERE id=?`;
    const values = [
      name,
      description,
      size,
      price_S,
      price_M,
      price_L,
      note,
      image,
      status,
      category_id,
      update_date,
      release_date,
      sales,
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
    const query = `SELECT * FROM product`;

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
};

export default ProductRepository;
