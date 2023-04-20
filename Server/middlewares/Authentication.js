import passport from 'passport'

import LocalStrategy from 'passport-local'
import passportJWT from 'passport-jwt'
import jwt from 'jsonwebtoken'
const JwtStrategy = passportJWT.Strategy;
const ExtractJwt = passportJWT.ExtractJwt;
const options = {
    jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
    secretOrKey: process.env.JWT_SECRET,
};

const Authen = ()=>{
    passport.use(
        new JwtStrategy(options, async (jwtPayload, done) => {
            try {
                console.log(jwtPayload.sub)
                const [rows] = await DB.pool().query('SELECT * FROM user WHERE id = ?', [
                    jwtPayload.sub,
                ]);
                console.log(rows)
                if (!rows.length) {
                    return done(null, false);
                }
                const user = rows[0];
                return done(null, user);
            } catch (err) {
                return done(err, false);
            }
        })
    );
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
}

export default Authen;
