import express from 'express';
import bodyParser from 'body-parser';
import RatingController from '../controllers/Rating.controller.js'
var jsonParser = bodyParser.json();
const RatingRoute = express.Router();

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
RatingRoute.get("/testAPI", RatingController.test)
RatingRoute.post("/create", RatingController.create)
RatingRoute.post("/update/:id", RatingController.update)
RatingRoute.delete("/delete/:id", RatingController.delete)
RatingRoute.get("/:id", RatingController.findRating)
RatingRoute.get("/", RatingController.findAll)
export default RatingRoute;

