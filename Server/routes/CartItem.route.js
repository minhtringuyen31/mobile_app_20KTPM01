import express from 'express';
import bodyParser from 'body-parser';
import CartItemController from '../controllers/CartItem.controller.js'
var jsonParser = bodyParser.json();
const CartItemRoute = express.Router();

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
CartItemRoute.get("/testAPI", CartItemController.test)
CartItemRoute.post("/create", CartItemController.create)
CartItemRoute.post("/update/:id", CartItemController.update)
CartItemRoute.delete("/delete/:id", CartItemController.delete)
CartItemRoute.get("/:id", CartItemController.findOne)
CartItemRoute.get("/", CartItemController.findAll)
export default CartItemRoute;

