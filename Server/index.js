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
import AuthenRoute from './routes/Authen.route.js';
import ToppingRoute from './routes/Topping.route.js';
import passport from 'passport'
import LocalStrategy from 'passport-local'
import passportJWT from 'passport-jwt'
import jwt from 'jsonwebtoken'
const JwtStrategy = passportJWT.Strategy;
const ExtractJwt = passportJWT.ExtractJwt;
import session from 'express-session'
dotenv.config();
const app = express();
const port = 3000;
DB.pool(); // mọi người nhớ đổi port database nhé. Port Database của Mac với Win
DB.connection();
app.use(express.json());

app.use(session({
  secret: 'keyboard cat',
  resave: false,
  saveUninitialized: false
}));
app.use(passport.initialize());
app.use(passport.session());

// passport.use(new LocalStrategy({
//   usernameField: 'phone',
//   passwordField: 'password'
// },
//   async function (phone, password, done) {

//     try {
//       const [rows] = await DB.pool().query('SELECT * FROM user WHERE phone = ?', [phone]);
//       if (!rows.length) {
//         return done(null, false, { message: 'Phone không đúng.' });
//       }
//       // if (!bcrypt.compareSync(password, rows[0].password)) {
//       //   return done(null, false, { message: 'Mật khẩu không đúng.' });
//       // }

//       if (password != rows[0].password) {
//         return done(null, false, { message: 'Mật khẩu không đúng.' });
//       }
//       return done(null, rows[0]);
//     } catch (error) {
//       if (err) { return done(err); }
//     }
//   }
// ));

// Configure the JWT strategy
const jwtOpts = {
  jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
  secretOrKey: "group06"
};

passport.use(new JwtStrategy(jwtOpts, (jwtPayload, done) => {
  // Check if user with ID in JWT payload exists in the database
  pool.query('SELECT * FROM user WHERE id = ?', jwtPayload.sub, (error, results) => {
    if (error) {
      return done(error, false);
    }
    if (!results.length) {
      return done(null, false);
    }
    const user = results[0];
    return done(null, user);
  });
}));

passport.serializeUser(function (user, done) {
  console.log('serializeUser user');
  done(null, user.id);
});

passport.deserializeUser(async function (id, done) {

  console.log('deserializing user');
  const [rows] = await DB.pool().query('SELECT * FROM user WHERE id = ?', [id]);
  try {
    done(null, rows[0]);
  } catch (error) {
    done(error, rows[0]);
  }

});

app.post('/login', (req, res) => {
  // Check if email and password are provided
  if (!req.body.phone || !req.body.password) {
    return res.status(400).json({ message: 'Phone and password are required' });
  }
  // Find user with matching email and password in the database
  DB.pool().query('SELECT * FROM user WHERE phone = ? AND password = ?', [req.body.phone, req.body.password])
    .then((results) => {
      if (!results.length) {
        return res.status(401).json({ message: 'Invalid phone or password' });
      }
      // Generate JWT token
      const user = results[0];
      const payload = { sub: user[0].id };
      const token = jwt.sign(payload, 'group06');
      // Send token to client
      return res.json({ token: token, userid: user[0].id });
    })
    .catch((error) => {
      console.log(error);
      return res.status(500).json({ message: 'Internal server error' });
    });
});

app.get('/test', ensureAuthenticated, function (req, res) {
  res.send("Da dang nhap");
})
function ensureAuthenticated(req, res, next) {
  if (req.isAuthenticated()) { return next(); }
  res.redirect('/authen');
}
app.get('/authen', function (req, res) {
  res.send("Ban khong co quyen!Vui long dang nhap");
})
app.use('/api/users', UserRoute);
app.use('/api/products', ProductRoute);
app.use('/api/orders', OrderRoute);
app.use('/api/orderProducts', OrderProductRoute);
app.use('/api/categories', CategoryRoute);
app.use('/api/carts', CartRoute);
app.use('/api/cartitems', CartItemRoute);
app.use('/api/ratings', RatingRoute);
app.use('/api/promotions', PromotionRoute);
app.use('/api/authen', AuthenRoute);
app.use('/api/toppings', ToppingRoute);
app.use('/api/admin/category', CategoryAdminRouter);
app.use('/api/admin/order', OrderAdminRouter);
app.use('/api/admin/product', ProductAdminRouter);
app.use('/api/admin/promotion', PromotionAdminRouter);
app.use('/api/admin/rating', RatingAdminRouter);
app.use('/api/admin/user', UserAdminRouter);

app.get('/', (req, res) => {
  res.send("123")
});

app.post('/logout', function (req, res, next) {
  req.logout(function (err) {
    if (err) { return next(err); }
    res.redirect('/login');
  });
});
app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});
// Ví dụ 1 luồng chạy User để mọi người dễ hiểu
// --> Khi npm start chương trình sẽ chạy tuần tự từ trên xuống dưới nên sẽ vào db() rồi app.use()
// app.use để định nghĩa đường dẫn
