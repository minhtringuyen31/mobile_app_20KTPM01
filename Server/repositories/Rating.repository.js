import DB from "../configs/db.js"
const RatingRepository = {
    async create(user_id, product_id, score, comment, create_at) {
        const query = `INSERT INTO rating (user_id, product_id, score, comment, create_at) VALUES (?, ?, ?, ?, ?)`;
        const values = [user_id, product_id, score, comment, create_at];
        try {
            DB.pool().query(query, values);
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    },
    async update(id, user_id, product_id, score, comment, create_at) {
        const query = `UPDATE rating SET user_id=?, product_id=?, score=?, comment=?, create_at=? WHERE id=?`;
        const values = [user_id, product_id, score, comment, create_at,id];

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
        const query = `SELECT * FROM rating`;

        try {
            const [rows] = await DB.pool().query(query);
            return rows;
        } catch (error) {
            console.error(error);
            return false;
        }
    },

    async findOneByID(id) {
        const query = `SELECT * FROM rating WHERE id = ?`;
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

export default RatingRepository;