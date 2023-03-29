import CartService from "../services/Cart.service.js"
const CartController = {
    
    async create(req, res) {
        const { user_id } = req.body;
        const newCart = await  CartService.create(user_id);
        if (newCart) {
            res.status(200).send(newCart)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async update(req, res) {
        const id = req.params.id;
        const { user_id } = req.body;
        const updateCart = await CartService.update(id, user_id);
        if (updateCart) {
            res.status(200).send(updateCart)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async delete(req, res) {
        const id = req.params.id;
        const status = await CartService.delete(id);
        if (status) {
            res.status(200).send({ status: 1, message: "Success" })
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }


    },
    async findOne(req, res) {
        const id = req.params.id;
        const cart = await CartService.findOne(id);
        if (cart) {
            res.status(200).send(cart)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async findAll(req, res) {
        const carts = await CartService.findAll();
        if (carts) {
            res.status(200).send(carts)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    test(req, res) {
        res.send("Test API from Cart")
    }


}

export default CartController;