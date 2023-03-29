import express from 'express';
import bodyParser from 'body-parser';
import CartController from '../controllers/Cart.controller.js'
var jsonParser = bodyParser.json();
const CartRoute = express.Router();

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
CartRoute.get("/testAPI", CartController.test)
CartRoute.post("/create", CartController.create)
CartRoute.post("/update/:id", CartController.update)
CartRoute.delete("/delete/:id", CartController.delete)
CartRoute.get("/:id", CartController.findOne)
CartRoute.get("/", CartController.findAll)
export default CartRoute;

