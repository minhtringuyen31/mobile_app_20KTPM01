import express from 'express';
import DB from './configs/db.js';
import * as dotenv from 'dotenv';
//User Router
import UserRoute from './routes/User.route.js';
import ProductRoute from './routes/Product.route.js';
import CategoryRoute from './routes/Category.route.js';
import CartRoute from './routes/Cart.route.js';
import CartItemRoute from './routes/CartItem.route.js';
import RatingRoute from './routes/Rating.route.js';
import PromotionRoute from './routes/Promotion.route.js';
import OrderRoute from './routes/Order.route.js';
import OrderProductRoute from './routes/OrderProduct.route.js';
//Admin Router
import CategoryAdminRouter from './routes/admin/Category.admin.route.js';
import OrderAdminRouter from './routes/admin/Order.admin.route.js';
import ProductAdminRouter from './routes/admin/Product.admin.route.js';
import PromotionAdminRouter from './routes/admin/Promotion.admin.route.js';
import RatingAdminRouter from './routes/admin/Rating.admin.route.js';
import UserAdminRouter from './routes/admin/User.admin.route.js';

dotenv.config();
const app = express();
const port = 3000;
DB.pool(); // mọi người nhớ đổi port database nhé. Port Database của Mac với Win
DB.connection();
app.use(express.json());
app.use('/api/users', UserRoute);
app.use('/api/products', ProductRoute);
app.use('/api/orders', OrderRoute);
app.use('/api/orderProducts', OrderProductRoute);
app.use('/api/categories', CategoryRoute);
app.use('/api/carts', CartRoute);
app.use('/api/cartitems', CartItemRoute);
app.use('/api/ratings', RatingRoute);
app.use('/api/promotions', PromotionRoute);

app.use('/api/admin/category', CategoryAdminRouter);
app.use('/api/admin/order', OrderAdminRouter);
app.use('/api/admin/product', ProductAdminRouter);
app.use('/api/admin/promotion', PromotionAdminRouter);
app.use('/api/admin/rating', RatingAdminRouter);
app.use('/api/admin/user', UserAdminRouter);

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});
// Ví dụ 1 luồng chạy User để mọi người dễ hiểu
// --> Khi npm start chương trình sẽ chạy tuần tự từ trên xuống dưới nên sẽ vào db() rồi app.use()
// app.use để định nghĩa đường dẫn
