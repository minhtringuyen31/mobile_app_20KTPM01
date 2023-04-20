import CategoryServices from '../../services/Category.service.js';

const CategoryAdminController = {
  disable: async (req, res) => {
    const id = req.params.id;
    const status = await CategoryServices.changeIsDisable(id, 1);
    if (status) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  enable: async (req, res) => {
    const id = req.params.id;
    const status = await CategoryServices.changeIsDisable(id, 0);
    if (status) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  create: async (req, res) => {
    const { category } = req.body;
    const newCategory = CategoryServices.create(category);
    if (newCategory) {
      res.status(200).send(newCategory);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  update: async (req, res) => {
    const id = req.params.id;
    const { name } = req.body;
    const updateCategory = await CategoryServices.update(id, name);
    if (updateCategory) {
      res.status(200).send(updateCategory);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  delete: async (req, res) => {
    const id = req.params.id;
    const status = await CategoryServices.delete(id);
    if (status) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findOne: async (req, res) => {
    const id = req.params.id;
    const category = await CategoryServices.findOne(id);
    if (category) {
      res.status(200).send(category);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findAll: async (req, res) => {
    const categories = await CategoryServices.findAll();
    if (categories) {
      res.status(200).send(categories);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
};

export default CategoryAdminController;
