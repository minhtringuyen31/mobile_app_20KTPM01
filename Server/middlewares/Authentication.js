import passport from 'passport'
import LocalStrategy from 'passport-local'
import crypto from 'crypto'
// passport.use(new LocalStrategy(function verify(phone, password, cb) {
//     db.get('SELECT * FROM user WHERE phone = ?', [phone], function (err, row) {
//         if (err) { return cb(err); }
//         if (!row) { return cb(null, false, { message: 'Incorrect username or password.' }); }

//         crypto.pbkdf2(password, row.salt, 310000, 32, 'sha256', function (err, hashedPassword) {
//             if (err) { return cb(err); }
//             if (!crypto.timingSafeEqual(row.hashed_password, hashedPassword)) {
//                 return cb(null, false, { message: 'Incorrect username or password.' });
//             }
//             return cb(null, row);
//         });
//     });
// }));

// router.post('/signup', function (req, res, next) {
//     var salt = process.env.SALT
//     crypto.pbkdf2(req.body.password, salt, 310000, 32, 'sha256', function (err, hashedPassword) {
//         if (err) { return next(err); }
//         db.run('INSERT INTO user (phone, password) VALUES (?, ?)', [
//             req.body.ph,
//             hashedPassword,
//         ], function (err) {
//             if (err) { return next(err); }
//             var user = {
//                 id: this.lastID,
//                 phone: req.body.phpne
//             };
//             req.login(user, function (err) {
//                 if (err) { return next(err); }
//                 res.redirect('/');
//             });
//         });
//     });
// });

// router.post('/logout', function (req, res, next) {
//     req.logout(function (err) {
//         if (err) { return next(err); }
//         res.redirect('/');
//     });
// });