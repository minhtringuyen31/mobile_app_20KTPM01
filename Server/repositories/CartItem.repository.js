import DB from '../configs/db.js';
const CartItemRepository = {
  async create(user_id, cart_id, product_id, quantity, size, price, topping, notes) {
    console.log("CartItemRepo" + user_id + cart_id + product_id + quantity + size, price + topping + notes)
    const query = `INSERT INTO cart_item (user_id, cart_id, product_id, quantity, size, price,topping,notes) VALUES (?, ?, ?, ?, ?,?,?,?)`;
    const values = [
      user_id,
      cart_id,
      product_id,
      quantity,
      size,
      price,
      topping,
      notes
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
    user_id,
    cart_id,
    product_id,
    quantity,
    size,
    price,
    topping
  ) {
    const query = `UPDATE cart_item SET user_id=?, cart_id=?, product_id=?, quantity=?, size=?,price=?,topping=? WHERE id=?`;
    const values = [
      user_id,
      cart_id,
      product_id,
      quantity,
      size,
      price,
      topping,
      id,
    ];

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
    const query = `DELETE FROM cart_item WHERE id=?`;

    try {
      await DB.pool().query(query, [id]);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },
  async removeAll(id) {
    const query = `DELETE FROM cart_item WHERE user_id=?`;
    try {
      await DB.pool().query(query, [id]);
      return true;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findAll() {
    const query = `SELECT cart_item.id,cart_item.user_id,cart_item.cart_id,cart_item.product_id,cart_item.quantity,cart_item.size,cart_item.price,cart_item.topping,product.name,product.description,product.image,category_id,notes FROM cart_item INNER JOIN product ON cart_item.product_id=product.id`;

    try {
      const [rows] = await DB.pool().query(query);
      return rows;
    } catch (error) {
      console.error(error);
      return false;
    }
  },

  async findOneByID(id) {
    const query = `SELECT * FROM cart_item WHERE id = ?`;
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

export default CartItemRepository;
