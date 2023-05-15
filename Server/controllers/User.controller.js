import UserServices from "../services/User.service.js"
import  bcrypt from 'bcrypt';
import nodemailer from 'nodemailer'

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
        const email = req.body.email;
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

            const check = await UserServices.findEmail( req.body.email)
            // console.log(checkphone)
            if(!check) 
                res.status(200).json({ message: "Email do not exist!" })
            
            else {
                ////console.log(req.body)
                //console.log(check.password)
                if(check.password === req.body.password) {
                    res.status(200).json({check, message: "Login success!"})
                }
                else res.status(200).json({ message: "Wrong password!" })
            }
            //console.log(req.body)
        }
        catch (error) {
            res.status(400).json({ message: "Wrong detail!" })
        }
    },

    // forgot password
    async vertifyEmail(req, res) {
        try{

            const check = await UserServices.findEmail( req.body.email)
            

            const data={email:req.body.email,otp:req.body.otp}
            if(!check) 
                res.status(200).json({ message: "email do not exist!" })
            
            else {
                console.log(req.body.otp)

                await UserServices.setOTP(data.email,data.otp)
                console.log(check)
                res.status(200).json(data)
            }
       
        }
        catch (error) {
            res.status(400).json({ message: "Wrong detail!" })
        }
    },
    async vertifyOTP(req,res){
        try {
            const checkotp= await UserServices.checkOTP(req.body.email,req.body.otp)
            const data={email:req.body.email,otp:req.body.otp}
            if (checkotp){
                res.status(200).json(data)
            }
            else {
                res.status(200).json({ message: "Error otp!" })

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
    //console.log(req.body)
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
            
            const checkEmail = await UserServices.findEmail( req.body.email)
            
            if(checkEmail) 
                res.status(200).json({ message: "Email has been used!" })
            else if (password!=confirmpass){
                    res.status(200).json({message:"Password and confirm password is not equal!"})
                }
            else {
                const hashedPassword = await bcrypt.hash(password, 6);
                await UserServices.signup(email, hashedPassword)
                res.status(200).json(data)
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
            const  {  newpassword, confirmpass } = req.body
            const id = req.params.id;
            console.log("id " + id)
            

            //console.log("id " + req.body.newpassword+ req.body.confirmpass)

            //const data= {  newpassword, confirmpass } 
            console.log(req.body)
            if(newpassword!=confirmpass) res.status(200).json({ message: "New password and confirm password is not equal!" })
            else {
                //console.log(req.body)   
                const hashedPassword = await bcrypt.hash(newpassword, 6);
                
                await UserServices.changepass(id,hashedPassword)
                console.log(hashedPassword)
                res.status(200).json({newpassword,confirmpass})    
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
            const id = req.params.id;
            const {  name,email,gender,date_of_birth,address } = req.body
            
            if(!name||!email||!gender||!date_of_birth||!address) res.status(200).json({ message: "One or more empty input data!" })
            else {
                console.log(req.body)   
                await UserServices.editprofile(id,name,email,gender,date_of_birth,address)
                res.status(200).json({  name,email,gender,date_of_birth,address })// { message: "Edit profile success!"})    
            }
        }
        catch (error) {
            res.status(400).json({ message: "Wrong detail!" })
        }
    },

    //GET /sendOTP
    async sendOTPGet(req,res){
        res.render("sendOTP")
    },


    //ngdjrtpcqtucojbj  // pass nick gmail nhom 
   
        async sendMail(req, res) {
            const transporter = nodemailer.createTransport({
              host: 'smtp.gmail.com',
              port: 587,
              secure: false,
              auth: {
                user: 'infinitycoffee06@gmail.com',
                pass: 'ngdjrtpcqtucojbj',
              },
              tls: {
                ciphers: 'SSLv3',
                minVersion: 'TLSv1.2',
              },
            });
          
            const email = req.body.email;

          
            // Generate OTP
            const otpLength = 4;
            let otp = '';
          
            for (let i = 0; i < otpLength; i++) {
              otp += Math.floor(Math.random() * 10); // Generate a random digit (0-9)
            }
          
            const check = await UserServices.findEmail(email);
          
            if (!check) {
              return res.status(200).json({ message: 'Email does not exist!' });
            }

            await UserServices.setPass(req.body.email)
            // const newpass=await UserServices.getPass(req.body.email)
          
          
            await UserServices.setOTP(req.body.email, otp);
            console.log(check);
            
            const mailOptions = {
              from: 'infinitycoffee06@gmail.com',
              to: email,
              subject: 'OTP Verification',
              text: `Your OTP is ${otp}\nEnter into Input to vertity your account.\n
              \n And this is NEW PASSWORD: "12345" \n Use this password for Login.\nKeep it secure, don't share it with anyone!Thank you!!!`,
            };
          
            // Send the email
            transporter.sendMail(mailOptions, (error, info) => {
              if (error) {
                console.error(error);
                return res.status(500).send('Error sending OTP');
              } else {
                console.log('OTP sent: ' + info.response);
                return res.status(200).json({ email: req.body.email, otp: otp });
              }
            });
          }
        }

export default UserController;