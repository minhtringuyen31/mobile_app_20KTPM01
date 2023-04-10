const AuthenController = {
    async login(req, res) {
        const informationUser = req.body;
        console.log(informationUser);
        if (informationUser.phone=="123"){
            informationUser.status=1;
        }
        res.json(req.body)
    },
    async signup(req, res) {


    },

}

export default AuthenController;