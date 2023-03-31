import express from 'express';
import bodyParser from 'body-parser';
import PromotionController from '../controllers/Promotion.controller.js'
var jsonParser = bodyParser.json();
const PromotionRoute = express.Router();

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
PromotionRoute.get("/testAPI", PromotionController.test)
PromotionRoute.post("/create", PromotionController.create)
PromotionRoute.post("/update/:id", PromotionController.update)
PromotionRoute.delete("/delete/:id", PromotionController.delete)
PromotionRoute.get("/:id", PromotionController.findOne)
PromotionRoute.get("/", PromotionController.findAll)
export default PromotionRoute;

