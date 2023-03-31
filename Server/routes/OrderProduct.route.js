import express from 'express';
import bodyParser from 'body-parser';
import OrderProductController from '../controllers/OrderProduct.controller.js'
var jsonParser = bodyParser.json();
const OrderProductRoute = express.Router();

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
OrderProductRoute.get("/testAPI", OrderProductController.test)
OrderProductRoute.post("/create", OrderProductController.create)
OrderProductRoute.post("/update/:id", OrderProductController.update)
OrderProductRoute.delete("/delete/:id", OrderProductController.delete)
OrderProductRoute.get("/:id", OrderProductController.findOne)
OrderProductRoute.get("/", OrderProductController.findAll)
export default OrderProductRoute;

