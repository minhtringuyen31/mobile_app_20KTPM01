import express from 'express';
import bodyParser from 'body-parser';
import OrderController from '../controllers/Order.controller.js'
var jsonParser = bodyParser.json();
const OrderRoute = express.Router();

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
OrderRoute.get("/testAPI", OrderController.test)
OrderRoute.post("/create", OrderController.create)
OrderRoute.post("/update/:id", OrderController.update)
OrderRoute.delete("/delete/:id", OrderController.delete)
OrderRoute.get("/:id", OrderController.findOne)
OrderRoute.get("/", OrderController.findAll)
export default OrderRoute;

