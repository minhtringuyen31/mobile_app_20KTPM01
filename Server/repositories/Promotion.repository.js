import DB from "../configs/db.js"
const PromotionRepository = {
    async create(name, description, discount, start_date, end_date) {
        const query = `INSERT INTO promotion (name, description, discount, start_date, end_date) VALUES (?, ?, ?, ?, ?)`;
        const values = [name, description, discount, start_date, end_date];
        try {
            DB.pool().query(query, values);
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    },
    async update(id, name, description, discount, start_date, end_date) {
        const query = `UPDATE promotion SET name=?, description=?, discount=?, start_date=?, end_date=? WHERE id=?`;
        const values = [name, description, discount, start_date, end_date, id];

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
        const query = `DELETE FROM promotion WHERE id=?`;

        try {
            await DB.pool().query(query, [id]);
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    },

    async findAll() {
        const query = `SELECT * FROM promotion`;

        try {
            const [rows] = await DB.pool().query(query);
            return rows;
        } catch (error) {
            console.error(error);
            return false;
        }
    },

    async findOneByID(id) {
        const query = `SELECT * FROM promotion WHERE id = ?`;
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

export default PromotionRepository;