import DB from "../configs/db.js"
const CartItemRepository = {
    async create(user_id, cart_id, product_id, quantity, size, price) {
        const query = `INSERT INTO cart_item (user_id, cart_id, product_id, quantity, size, price) VALUES (?, ?, ?, ?, ?,?)`;
        const values = [user_id, cart_id, product_id, quantity, size, price];
        try {
            DB.pool().query(query, values);
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    },
    async update(id, user_id, cart_id, product_id, quantity, size, price) {
        const query = `UPDATE cart_item SET user_id=?, cart_id=?, product_id=?, quantity=?, size=?,price=? WHERE id=?`;
        const values = [user_id, cart_id, product_id, quantity, size, price, id];

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

    async findAll() {
        const query = `SELECT * FROM cart_item`;

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
    }


}

export default CartItemRepository;