import ToppingService from '../../services/Topping.service.js';

const ToppingAdminController = {
  create: async (req, res) => {
    const { category_id, name, price, checked } = req.body;
    const newTopping = await ToppingService.create(
      category_id,
      name,
      price,
      checked
    );
    if (newTopping) {
      res.status(200).send(newTopping);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  update: async (req, res) => {
    const id = req.params.id;
    const { category_id, name, price, checked } = req.body;
    const updateTopping = await ToppingService.update(
      id,
      category_id,
      name,
      price,
      checked
    );
    if (updateTopping) {
      res.status(200).send(updateTopping);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  delete: async (req, res) => {
    const id = req.params.id;
    const deleteTopping = await ToppingService.delete(id);
    if (deleteTopping) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findOne: async (req, res) => {
    const id = req.params.id;
    const topping = await ToppingService.findOne(id);
    if (topping) {
      res.status(200).send(topping);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findAll: async (req, res) => {
    const toppings = await ToppingService.findAll();
    if (toppings) {
      res.status(200).send(toppings);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
};

export default ToppingAdminController;
