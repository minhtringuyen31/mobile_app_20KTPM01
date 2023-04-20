import UserRepository from "../repositories/User.respository.js";

const AuthenController = {
    async login(req, res) {
        const informationUser = req.body;
        console.log(informationUser);
        if (informationUser.phone=="123"){
            informationUser.status=1;
        }   
        res.json(req.body)
    },

    async Login(req,res){
        const id =  req.params.id;
        const user= await UserRepository.findOneByID(id)
        if ( user.phone == req.body.phone && user.password==req.body.password && user.role==0)
        {
            // user
            
        }
        else if (user.phone == req.body.phone && user.password==req.body.password && user.role==1){
            //admin
        }
        else{
            // dang nhap that bai
            res.status(200).json("Wrong ")
        }


    }

}

export default AuthenController;