import NotificationRepository from "../repositories/Notification.repository.js"

const NotificationService = {
    async getAllByUserId(userId) {
        return await NotificationRepository.getAllByUserId(userId)
    },

    async postNotification(id, user_id, title, sub_title, image, description, time, type, is_seen) {
        return await NotificationRepository.postNotification(id, user_id, title, sub_title, image, description, time, type, is_seen)
    }
}
export default NotificationService