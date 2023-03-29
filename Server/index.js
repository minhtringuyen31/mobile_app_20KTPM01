import express from "express";
import DB from "./configs/db.js"
import UserRoute from './routes/User.route.js'
import ProductRoute from "./routes/Product.route.js";
import CategoryRoute from "./routes/Category.route.js";
import CartRoute from "./routes/Cart.route.js";
import CartItemRoute from "./routes/CartItem.route.js";
import RatingRoute from "./routes/Rating.route.js";
import PromotionRoute from "./routes/Promotion.route.js";
import OrderRoute from "./routes/Order.route.js";
import OrderProductRoute from "./routes/OrderProduct.route.js";
const app = express()
const port = 3000
DB.pool();// mọi người nhớ đổi port database nhé. Port Database của Mac với Win 
app.use(express.json())
app.use('/api/users', UserRoute)
app.use('/api/products', ProductRoute)
app.use('/api/orders', OrderRoute)
app.use('/api/orderProducts', OrderProductRoute)
app.use('/api/categories', CategoryRoute)
app.use('/api/carts', CartRoute)
app.use('/api/cartitems', CartItemRoute)
app.use('/api/ratings', RatingRoute)
app.use('/api/promotions', PromotionRoute)

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})
// Ví dụ 1 luồng chạy User để mọi người dễ hiểu
// --> Khi npm start chương trình sẽ chạy tuần tự từ trên xuống dưới nên sẽ vào db() rồi app.use()
// app.use để định nghĩa đường dẫn  