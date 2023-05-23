import DB from '../configs/db.js';
const NotificationRepository = {
    async getAllByUserId(userId) {
        const query = `SELECT * FROM notification WHERE notification.user_id = ? ORDER BY time DESC `
        try {
            const [rows] = await DB.pool().query(query, [userId]);
            console.log(rows)
            return rows;
        } catch (error) {
            console.error(error);
            return false;
        }

    },

    async postNotification(id, user_id, title, sub_title, image, description, time, type, is_seen) {
        const query = `INSERT INTO notification (id, user_id, title, sub_title, image, description, time, type, is_seen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)`;
        const values = [id, user_id, title, sub_title, image, description, time, type, is_seen]
        try {
            DB.pool().query(query, values);
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    }


}

export default NotificationRepository