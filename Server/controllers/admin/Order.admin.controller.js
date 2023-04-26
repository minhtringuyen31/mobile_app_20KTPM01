import OrderServices from '../../services/Order.service.js';

const OrderAdminController = {
  status: async (req, res) => {
    const id = req.params.id;
    const status = req.body.status;
    const changeStatus = await OrderServices.changeStatus(id, status);
    if (changeStatus) {
      res.status(200).send(changeStatus);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  create: async (req, res) => {
    const {
      user_id,
      order_date,
      shipping_address,
      total,
      status,
      promotion_id,
      payment_method_id,
    } = req.body;
    const newOrder = await OrderServices.create(
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
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  update: async (req, res) => {
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
    const updateOrder = await OrderServices.update(
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
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  delete: async (req, res) => {
    const id = req.params.id;
    const status = await OrderServices.delete(id);
    if (status) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findOne: async (req, res) => {
    const id = req.params.id;
    const order = await OrderServices.findOne(id);
    if (order) {
      res.status(200).send(order);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findAll: async (req, res) => {
    const orders = await OrderServices.findAll();
    if (orders) {
      res.status(200).send(orders);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
};

export default OrderAdminController;
