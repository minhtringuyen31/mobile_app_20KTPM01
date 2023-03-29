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
            res.status(200).send({ status: 1, message: "Success" })
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
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
    }


}

export default UserController;