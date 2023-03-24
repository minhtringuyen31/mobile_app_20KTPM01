import express from 'express';
import bodyParser from 'body-parser';
import UserController from '../controllers/User.controller.js'

// /../models/Account.model.js
var jsonParser = bodyParser.json();
const UserRoute = express.Router();

UserRoute.get("/add", UserController.add)
export default UserRoute;

