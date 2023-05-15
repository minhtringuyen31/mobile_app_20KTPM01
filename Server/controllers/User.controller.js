import UserServices from "../services/User.service.js"
import bcrypt from 'bcrypt';

const UserController = {
    async create(req, res) {
        const { name, gender, email, phone, password, date_of_birth, address, avatar, role, is_disable } = req.body
        console.log(req.body)
        const newUser = await UserServices.create(name, gender, email, phone, password, date_of_birth, address, avatar, role, is_disable)
        if (newUser) {
            res.status(200).send(req.body);
        }
        else {
            res.status(404).send({ status: 0, message: "Failed" });
        }


    },
    async update(req, res) {
        const id = req.params.id;
        const { name, gender, email, phone, password, date_of_birth, address, avatar, role, is_disable } = req.body
        const userUpdate = await UserServices.update(id, name, gender, email, phone, password, date_of_birth, address, avatar, role, is_disable)
        if (userUpdate) {
            res.status(200).send(userUpdate);
        }
        else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async delete(req, res) {
        const id = req.params.id;
        const status = await UserServices.delete(id)
        if (status) {
            res.status(200).send(status)
        } else {
            res.status(404).send(status);
        }


    },
    async findOne(req, res) {
        const id = req.params.id;
        const user = await UserServices.findOne(id)
        if (user) {
            res.status(200).send(user);
        }
        else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    // async findPhone(req, res) {
    //     const phone = req.params.phone;
    //     const user = await UserServices.findPhone(phone)
    //     if (user) {
    //         res.status(200).send(user);
    //     }
    //     else {
    //         res.status(404).send({ status: 0, message: "Failed" });
    //     }

    // },
    async findAll(req, res) {
        const users = await UserServices.findAll()
        if (users) {
            res.status(200).send(users);
        }
        else {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },
    test(req, res) {

        return res.send("Test API from USER")
    },



    //GET / login
    async loginGet(req, res) {
        res.render('login')
    },

    //POST / login
    async loginPost(req, res) {
        try {

            const check = await UserServices.findPhone(req.body.phone)
            // console.log(checkphone)
            if (!check)
                res.status(200).json({ message: "phone do not exist!" })

            else {
                ////console.log(req.body)
                //console.log(check.password)
                if (check.password === req.body.password) {
                    res.status(200).json({ check, message: "Login success!" })
                }
                else res.status(200).json({ message: "Wrong password!" })
            }
            //console.log(req.body)
        }
        catch (error) {
            res.status(400).json({ message: "Wrong detail!" })
        }
    },


    //GET /signup
    signupGet(req, res) {
        res.render('signup');
    },

    //POST /signup
    async signupPost(req, res) {
        //console.log(req.body)
        const { phone, password, confirmpass } = req.body

        if (!phone || typeof phone !== 'string') res.status(400).json({ message: "Empty phone!" })

        if (!password || typeof password !== 'string') res.status(400).json({ message: "Empty password!" })

        if (!confirmpass || typeof confirmpass !== 'string') res.status(400).json({ message: "Empty confirm password!" })

        try {
            const data = {
                phone,
                password,
                confirmpass
            }

            const checkphone = await UserServices.findPhone(req.body.phone)

            if (checkphone)
                res.status(200).json({ message: "phone has been used!" })
            else if (password != confirmpass) {
                res.status(200).json({ message: "Password and confirm password is not equal!" })
            }
            else {
                const hashedPassword = await bcrypt.hash(password, 6);
                await UserServices.signup(phone, hashedPassword)
                res.status(200).json(data)
            }
        }
        catch (error) {
            // if(error.code === 11000) {
            res.status(400).json({ message: error })
            //   }
        }
    },

    //GET / changepassword
    async changepassGet(req, res) {
        res.render('changepassword')
    },

    //POST / changepassword
    async changepassPost(req, res) {

        try {
            const { newpassword, confirmpass } = req.body
            const id = req.params.id;
            console.log("id " + id)


            //console.log("id " + req.body.newpassword+ req.body.confirmpass)

            //const data= {  newpassword, confirmpass } 
            console.log(req.body)
            if (newpassword != confirmpass) res.status(200).json({ message: "New password and confirm password is not equal!" })
            else {
                //console.log(req.body)   
                const hashedPassword = await bcrypt.hash(newpassword, 6);

                await UserServices.changepass(id, hashedPassword)
                console.log(hashedPassword)
                res.status(200).json({ newpassword, confirmpass })
            }
        }
        catch (error) {
            res.status(400).json({ message: "Wrong detail!" })
        }
    },
    //GET / editprofile
    async editprofileGet(req, res) {
        res.render('editprofile')
    },

    //POST / editprofile
    async editprofilePost(req, res) {
        try {
            const id = req.params.id;
            const { name, email, gender, date_of_birth, address } = req.body

            if (!name || !email || !gender || !date_of_birth || !address) res.status(200).json({ message: "One or more empty input data!" })
            else {
                console.log(req.body)
                await UserServices.editprofile(id, name, email, gender, date_of_birth, address)
                res.status(200).json({ name, email, gender, date_of_birth, address })// { message: "Edit profile success!"})    
            }
        }
        catch (error) {
            res.status(400).json({ message: "Wrong detail!" })
        }
    },
    //POST / editprofile
    async saveTokenFireBase(req, res) {
        try {
            const tokenFireBase = req.body;
            var check = await UserServices.findTokenByUserID(tokenFireBase.user_id)
            console.log(check);
            if (check) {
                await UserServices.updateTokenFireBase(tokenFireBase.token,tokenFireBase.user_id)
            } else {
                var result = await UserServices.saveTokenFirebase(tokenFireBase.user_id, tokenFireBase.token)
                res.status(200).json(result)
            }
        }
        catch (error) {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },



    async findAllTokenFireBase(req, res) {
        try {
            console.log("query,queryCon");
            var result = await UserServices.finAllToken()
            res.status(200).json(result)

        }
        catch (error) {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },

    async findOneTokenFireBase(req, res) {
        try {
            const id = req.params.id;
            const tokenFireBase = req.body;
            var result = await UserServices.findOneToken(id)
            res.status(200).json(result)

        }
        catch (error) {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },

    async sendNotification(req, res) {
        try {
            
            const user_id = req.body.user_id;
            const message = req.body.message;
            const target = req.body.target;

            var result = await UserServices.handleTokenFireBase(user_id,message,target)
            res.status(200).json({ status: 1, message: "Thông báo thành công" })

        }
        catch (error) {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },



    //GET /sendOTP
    async sendOTPGet(req, res) {
        res.render("sendOTP")
    }

    //POST / sendOTP
    // async sendOTPPost(req,res){
    //     try{
    //         const check = await UserServices.findPhone( req.body.phone)
    //         // console.log(checkphone)
    //         if(!check) 
    //             res.status(200).json({ message: "Phone do not exist!" })

    //         else {

    //         }

    //     }
    //     catch(error){
    //         res.status(400).json({ message: "Wrong detail!" })
    //     }
    // }


}

export default UserController;