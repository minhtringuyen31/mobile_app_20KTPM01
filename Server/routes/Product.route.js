import express from 'express';
import bodyParser from 'body-parser';
import ProductController from '../controllers/Product.controller.js'
var jsonParser = bodyParser.json();
const ProductRoute = express.Router();

ProductRoute.get("/favorite/check", ProductController.isExistedFavProduct)
ProductRoute.get("/favorite/:userId", ProductController.findAllFavProduct)

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
// ProductRoute.get("/testAPI", ProductController.test)
ProductRoute.post("/create", ProductController.create)
ProductRoute.post("/update/:id", ProductController.update)
ProductRoute.delete("/delete/:id", ProductController.delete)
ProductRoute.get("/:id", ProductController.findOne)
ProductRoute.get("/", ProductController.findAll)
ProductRoute.post("/favorite/add", ProductController.addNewFavProduct)
ProductRoute.post("/favorite/remove", ProductController.removeFavProduct)
export default ProductRoute;

