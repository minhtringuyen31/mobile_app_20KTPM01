import DB from '../configs/db.js';
import jwt from 'jsonwebtoken'
const AuthenController = {
    async login(req, res) {
        // const informationUser = req.body;
        // console.log(informationUser);
        // if (informationUser.phone=="123"){
        //     informationUser.status=1;
        // }
        // res.json(req.body)

        try {
            const [rows] = await DB.pool().query('SELECT * FROM user WHERE phone = ?', [
                req.body.phone,
            ]);
            if (!rows.length) {
                return res.status(401).json({ message: 'Invalid email or password' });
            }
            const user = rows[0];
            // const passwordIsValid = bcrypt.compareSync(req.body.password, user.password);
            const passwordIsValid = req.body.password == user.password ? 1 : 0
            if (!passwordIsValid) {
                return res.status(401).json({ message: 'Invalid email or password' });
            }
            const token = jwt.sign({ sub: user.id }, process.env.JWT_SECRET, {
                expiresIn: '1h',
            });
            return res.json({ token: token, userId: user.id, role: user.role, status: 1 });
        } catch (err) {
            console.error(err);
            return res.status(500).json({ message: 'Internal server error' });
        }
    },
    async signup(req, res) {


    },

}

export default AuthenController;