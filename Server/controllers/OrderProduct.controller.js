import OrderProductService from "../services/OrderProduct.service.js"
const OrderProductController = {
    async create(req, res) {

        const { note, order_id, product_id, quantity, price } = req.body;
        const newOrderProduct = await OrderProductService.create(note, order_id, product_id, quantity, price);
        if (newOrderProduct) {
            res.status(200).send(newOrderProduct)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async update(req, res) {
        const id = req.params.id;
        const { note, order_id, product_id, quantity, price } = req.body;
        const updateOrderProduct = await  OrderProductService.update(id, note, order_id, product_id, quantity, price);
        if (updateOrderProduct) {
            res.status(200).send(updateOrderProduct)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async delete(req, res) {
        const id = req.params.id;
        const status = await OrderProductService.delete(id);
        if (status) {
            res.status(200).send({ status: 1, message: "Success" })
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }


    },
    async findOne(req, res) {
        const id = req.params.id;
        const orderProduct = await OrderProductService.findOne(id);
        if (orderProduct) {
            res.status(200).send(orderProduct)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async findAll(req, res) {
        const orderProducts = await OrderProductService.findAll();
        if (orderProducts) {
            res.status(200).send(orderProducts)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    test(req, res) {
        res.send("Test API from OrderProduct")
    }


}

export default OrderProductController;