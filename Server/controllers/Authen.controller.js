import DB from '../configs/db.js';
import jwt from 'jsonwebtoken'
import bcrypt from 'bcrypt'
import UserServices from "../services/User.service.js"
import { OAuth2Client } from 'google-auth-library';
import CartServices from '../services/Cart.service.js';
const client = new OAuth2Client("315513977204-r4d598sk0sv9fifrhefveulu7ksi8fsg.apps.googleusercontent.com");
const AuthenController = {
    async login(req, res) {
        try {
            const [rows] = await DB.pool().query('SELECT * FROM user WHERE email = ?', [
                req.body.email,
            ]);
            if (!rows.length) {
                return res.status(401).json({ message: 'Invalid email or password' });
            }
            const user = rows[0];
            const passwordIsValid = bcrypt.compareSync(req.body.password, user.password);
            if (!passwordIsValid) {
                return res.status(401).json({ message: 'Invalid email or password' });
            }
            const token = jwt.sign({ sub: user.id }, process.env.JWT_SECRET, {
                expiresIn: '1h',
            });
            console.log(user);
            return res.json({ token: token, userId: user.id, role: user.role, status: 1 });
        } catch (err) {
            console.error(err);
            return res.status(500).json({ message: 'Internal server error' });
        }
    },

    async Login(req, res) {
        const id = req.params.id;
        const user = await UserRepository.findOneByID(id)
        if (user.phone == req.body.phone && user.password == req.body.password && user.role == 0) {
            // user

        }
        else if (user.phone == req.body.phone && user.password == req.body.password && user.role == 1) {
            //admin
        }
        else {
            // dang nhap that bai
            res.status(200).json("Wrong ")
        }


    },
    async LoginGG(req, res) {
        //
        const ticket = await client.verifyIdToken({
            idToken: req.params.id,
            audience: process.env.CLIENT_ID,  // Specify the CLIENT_ID of the app that accesses the backend
            // Or, if multiple clients access the backend:
            //[CLIENT_ID_1, CLIENT_ID_2, CLIENTCLIENT_ID_ID_3]
        });
        const payload = ticket.getPayload();
        const userid = payload['hd'];

        let user = await UserServices.findEmail(payload.email)

        if (!user) {
            // 
            user = await UserServices.create(payload.name, null, payload.email, null, null, null, null, payload.picture, 0, 0)
            await CartServices.create(user.id)
            const token = jwt.sign({ sub: user.id }, process.env.JWT_SECRET, {
                expiresIn: '365d',
            });
            return res.json({ token: token, userId: user.id, role: user.role, status: 1 });
        }
        const token = jwt.sign({ sub: user.id }, process.env.JWT_SECRET, {
            expiresIn: '365d',
        });
        return res.json({ token: token, userId: user.id, role: user.role, status: 1 });

    }

}

export default AuthenController;