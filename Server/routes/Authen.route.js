import express from 'express';
import bodyParser from 'body-parser';
import AuthenController from '../controllers/Authen.controller.js'
var jsonParser = bodyParser.json();
const AuthenRoute = express.Router();

// Ở đây mọi người có thể sử dụng,get,post,delete,update --> Tuỳ vào mục đích để sử dụng
AuthenRoute.post("/login", AuthenController.login)
AuthenRoute.post("/tokensignin/:id", AuthenController.verify)
//AuthenRoute.post("/signup", AuthenController.signup)
export default AuthenRoute;

