import OrdertService from "../services/Order.service.js";
const OrderController = {
  async create(req, res) {
    const {
      user_id,
      order_date,
      shipping_address,
      total,
      status,
      promotion_id,
      payment_method_id,
    } = req.body;
    const newOrder = await OrdertService.create(
      user_id,
      order_date,
      shipping_address,
      total,
      status,
      promotion_id,
      payment_method_id
    );
    if (newOrder) {
      res.status(200).send(newOrder);
    } else {
      res.status(404).send({ status: 0, message: "Failed" });
    }
  },
  async update(req, res) {
    const id = req.params.id;
    const {
      user_id,
      order_date,
      shipping_address,
      total,
      status,
      promotion_id,
      payment_method_id,
    } = req.body;
    const updateOrder = await OrdertService.update(
      id,
      user_id,
      order_date,
      shipping_address,
      total,
      status,
      promotion_id,
      payment_method_id
    );
    if (updateOrder) {
      res.status(200).send(updateOrder);
    } else {
      res.status(404).send({ status: 0, message: "Failed" });
    }
  },
  async delete(req, res) {
    const id = req.params.id;
    const status = await OrdertService.delete(id);
    if (status) {
      res.status(200).send({ status: 1, message: "Success" });
    } else {
      res.status(404).send({ status: 0, message: "Failed" });
    }
  },
  async findOne(req, res) {
    const id = req.params.id;
    const order = await OrdertService.findOne(id);
    if (order) {
      res.status(200).send(order);
    } else {
      res.status(404).send({ status: 0, message: "Failed" });
    }
  },
  async findAll(req, res) {
    const orders = await OrdertService.findAll();   
    if (orders) {
      res.status(200).send(orders);
    } else {
      res.status(404).send({ status: 0, message: "Failed" });
    }
  },
  test(req, res) {
    res.send("Test API from Order");
  },
};

export default OrderController;
