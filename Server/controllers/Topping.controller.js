import ToppingServices from "../services/Topping.service.js";

const ToppingController = {
    async create(req, res) {
        const { category_id, name, price, checked } = req.body;
        const newTopping = await ToppingServices.create(category_id, name, price, checked);
        if (newTopping) {
            res.send(newTopping)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async update(req, res) {
        const id = req.params.id;
        const { category_id, name, price, checked } = req.body;
        const updateTopping = await ToppingServices.update(id, category_id, name, price, checked);
        if (updateTopping) {
            res.send(updateTopping)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },
    async delete(req, res) {
        const id = req.params.id;
        const deleteTopping = await ToppingServices.delete(id);
        if (deleteTopping) {
            res.status(200).send({ status: 1, message: "Success" })
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }


    },
    async findOne(req, res) {
        const id = req.params.id;
        const topping = await ToppingServices.findOne(id);
        if (topping) {
            res.status(200).send(topping)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async findAll(req, res) {
        const toppings = await ToppingServices.findAll();
        if (toppings) {
            res.status(200).send(toppings)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    test(req, res) {
        res.send("Test API from Topping");
    },
};

export default ToppingController;
