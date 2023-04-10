import express from 'express';
import bodyParser from 'body-parser';
import CategoryController from '../controllers/Category.controller.js'
import uploadCloud from '../middlewares/uploader.js';
var jsonParser = bodyParser.json();
const CategoryRoute = express.Router();

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
CategoryRoute.get("/testAPI", CategoryController.test)
CategoryRoute.post("/create", uploadCloud.single("image") ,CategoryController.create)
CategoryRoute.post("/update/:id", CategoryController.update)
CategoryRoute.delete("/delete/:id", CategoryController.delete)
CategoryRoute.get("/:id", CategoryController.findOne)
CategoryRoute.get("/", CategoryController.findAll)
export default CategoryRoute;

