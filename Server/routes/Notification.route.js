import express from 'express';
import bodyParser from 'body-parser';
import NotificationController from '../controllers/Notification.controller.js';
var jsonParser = bodyParser.json();
const NotificationRoute = express.Router();


NotificationRoute.get("/:userId", NotificationController.getNotification);
NotificationRoute.post("/", NotificationController.postNotification)

export default NotificationRoute;