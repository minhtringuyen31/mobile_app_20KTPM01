import CartItemService from "../services/CartItem.service.js"
const CartItemController = {
    async create(req, res) {
        const { user_id, cart_id, product_id, quantity, size, price,topping,notes } = req.body;
        const newCartItem = await CartItemService.create(user_id, cart_id, product_id, quantity, size, price, topping, notes);
        if (newCartItem) {
            res.status(200).send(newCartItem)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },  
    async update(req, res) {
        const id = req.params.id;   
        const { user_id, cart_id, product_id, quantity, size, price ,topping} = req.body;
        const updateCartItem = await CartItemService.update(id, user_id, cart_id, product_id, quantity, size, price,topping);
        if (updateCartItem) {
            res.status(200).send(updateCartItem)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async delete(req, res) {
        req.app.io.emit("server-send-message", "Hello");
        const id = req.params.id;   
        const status = await CartItemService.delete(id);
        if (status) {
            res.status(200).send({ status: 1, message: "Success" })
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }


    },
    async findOne(req, res) {
        const id = req.params.id;
        const cart = await CartItemService.findOne(id);
        if (cart) {
            res.status(200).send(cart)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async findAll(req, res) {
        const carts = await CartItemService.findAll();
        if (carts) {
            res.status(200).send(carts)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    test(req, res) {
        res.send("Test API from CartItem")
    }


}

export default CartItemController;