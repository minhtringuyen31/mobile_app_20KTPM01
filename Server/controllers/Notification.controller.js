import NotificationService from "../services/Notification.service.js";

const NotificationController = {
    async getNotification(req, res) {
        const userId = req.params.userId
        console.log("Noti userId: " + userId)
        const result = await NotificationService.getAllByUserId(userId)
        if (result) {
            res.status(200).send(result);
        }
        else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },

    async postNotification(req, res) {
        const { id, user_id, title, sub_title, image, description, time, type, is_seen } = req.body

        const result = await NotificationService.postNotification(id, user_id, title, sub_title, image, description, time, type, is_seen)

        if (result) {
            res.status(200).send(true)
        } else {
            res.status(404).send(false);
        }
    }

}
export default NotificationController;