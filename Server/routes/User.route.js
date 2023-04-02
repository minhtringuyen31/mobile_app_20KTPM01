import express from 'express';
import bodyParser from 'body-parser';
import UserController from '../controllers/User.controller.js'
var jsonParser = bodyParser.json();
const UserRoute = express.Router();

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
UserRoute.get("/testAPI", UserController.test) 
UserRoute.post("/create", UserController.create)
UserRoute.post("/update/:id", UserController.update)
UserRoute.delete("/delete/:id", UserController.delete)
UserRoute.get("/:id", UserController.findOne)
UserRoute.get("/", UserController.findAll)

export default UserRoute;
