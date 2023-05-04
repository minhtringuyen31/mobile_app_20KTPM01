import OrderProductService from '../../services/OrderProduct.service.js';

const OrderProductAdminController = {
  create: async (req, res) => {
    const { note, order_id, product_id, quantity, price } = req.body;
    const newOrderProduct = await OrderProductService.create(
      note,
      order_id,
      product_id,
      quantity,
      price
    );
    if (newOrderProduct) {
      res.status(200).send(newOrderProduct);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  update: async (req, res) => {
    const id = req.params.id;
    const { note, order_id, product_id, quantity, price } = req.body;
    const updateOrderProduct = await OrderProductService.update(
      id,
      note,
      order_id,
      product_id,
      quantity,
      price
    );
    if (updateOrderProduct) {
      res.status(200).send(updateOrderProduct);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  delete: async (req, res) => {
    const id = req.params.id;
    const deleteOrderProduct = await OrderProductService.delete(id);
    if (deleteOrderProduct) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findOne: async (req, res) => {
    const id = req.params.id;
    const orderProduct = await OrderProductService.findOne(id);
    if (orderProduct) {
      res.status(200).send(orderProduct);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findAll: async (req, res) => {
    const orderProducts = await OrderProductService.findAll();
    if (orderProducts) {
      res.status(200).send(orderProducts);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findAllByOrderID: async (req, res) => {
    const order_id = req.params.order_id;
    const orderProducts = await OrderProductService.findOrderProductByOrderId(order_id);
    if (orderProducts) {
      res.status(200).send(orderProducts);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
};

export default OrderProductAdminController;
