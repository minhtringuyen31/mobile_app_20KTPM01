import UserServices from "../services/User.service.js"
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
    async findEmail(req, res) {
        const email = req.params.email;
        const user = await UserServices.findEmail(email)
        if (user) {
            res.status(200).send(user);
        }
        else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
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
    async loginGet(req,res){
        res.render('login')
    },

    //POST / login
    async loginPost(req, res) {
        try{

            const check = await UserServices.findEmail({email: req.body.email}).lean()
            
            if(!check) res.status(200).json({ message: "Email does not exist!" })
            else {
                console.log(req.body)
                if(check.password === req.body.password) {
                    res.status(200).json({check, message: "Login success!"})
                }
                else res.status(200).json({ message: "Wrong password!" })
            }
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
        const { email, password, confirmpass } = req.body

        if (!email || typeof email !== 'string') res.status(400).json({message: "Empty email!"})

        if (!password || typeof password !== 'string') res.status(400).json({message: "Empty password!"})

        if (!confirmpass || typeof confirmpass !== 'string') res.status(400).json({message: "Empty confirm password!"})
        
        try {
            const data =  {
                email,
                password,
                confirmpass
            }
            
            const checkEmail = await UserServices.findEmail({email: req.body.email}).lean()
            
            if(checkEmail) 
                res.status(200).json({ message: "Email has been used!" })
            else if (password!=confirmpass){
                    res.status(200).json({message:"Password and confirm password is not equal!"})
                }
            else {
                await UserServices.signup(email,password)
                res.status(200).json({data, message: "Signup success!"})
            }
        }
        catch(error) {
            // if(error.code === 11000) {
                res.status(400).json({message: error})
         //   }
        }
    },

    //GET / changepassword
    async changepassGet(req,res){
        res.render('changepassword')
    },

    //POST / changepassword
    async changepassPost(req, res) {
        try{
            const {  newpassword, confirmpass } = req.body
            
            if(newpassword!=confirmpass) res.status(200).json({ message: "New password and confirm password is not equal!" })
            else {
                console.log(req.body)   
                await UserServices.changepass(newpassword)
                res.status(200).json({newpassword, message: "Change password success!"})    
            }
        }
        catch (error) {
            res.status(400).json({ message: "Wrong detail!" })
        }
    },
    //GET / editprofile
    async editprofileGet(req,res){
        res.render('editprofile')
    },

    //POST / editprofile
    async editprofilePost(req, res) {
        try{
            const {  name,email,gender,date_of_birth,phone,address } = req.body
            
            if(!name||!email||!gender||!date_of_birth||!phone||!address) res.status(200).json({ message: "One or more empty input data!" })
            else {
                console.log(req.body)   
                await UserServices.editprofile(name,email,gender,date_of_birth,phone,address)
                res.status(200).json({newpassword, message: "Edit profile success!"})    
            }
        }
        catch (error) {
            res.status(400).json({ message: "Wrong detail!" })
        }
    },



}

export default UserController;