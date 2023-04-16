import express from 'express';
import bodyParser from 'body-parser';
import ToppingController from '../controllers/Topping.controller.js'
var jsonParser = bodyParser.json();
const ToppingRoute = express.Router();

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
ToppingRoute.get("/testAPI", ToppingController.test)
ToppingRoute.post("/create", ToppingController.create)
ToppingRoute.post("/update/:id", ToppingController.update)
ToppingRoute.delete("/delete/:id", ToppingController.delete)
ToppingRoute.get("/:id", ToppingController.findOne)
ToppingRoute.get("/", ToppingController.findAll)
export default ToppingRoute;        

