import DB from "../configs/db.js"
const UserRepository = {
    async create(name, gender, email, phone, password, date_of_birth = "", address = "", avatar = "", role = "0", is_disable = "false") {
        const query = `INSERT INTO user (name, gender,email,phone,password, date_of_birth, address, avatar, role,is_disable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`;
        const values = [name, gender, email, phone, password, date_of_birth, address, avatar, role, is_disable];
        try {
            DB.pool().query(query, values);
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    },
    async update(id, name, gender, email, phone, password, date_of_birth, address, avatar, role, is_disable) {
        const query = `UPDATE user SET name=?, gender=?, email=?, phone=?, password=?, date_of_birth=?, address=?, avatar=?, role=?, is_disable=? WHERE id=?`;
        const values = [name, gender, email, phone, password, date_of_birth, address, avatar, role, is_disable, id];

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
        const query = `DELETE FROM user WHERE id=?`;
        try {
            await DB.pool().query(query, [id]);
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    },

    async findAll() {
        const query = `SELECT * FROM user`;

        try {
            const [rows] = await DB.pool().query(query);
            return rows;
        } catch (error) {
            console.error(error);
            return false;
        }
    },

    async findOneByID(id) {
        const query = `SELECT * FROM user WHERE id = ?`;
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

export default UserRepository;